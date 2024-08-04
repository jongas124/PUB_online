package com.PUB_Online.PUB.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PUB_Online.PUB.controllers.dtos.ItemPedidoDTO;
import com.PUB_Online.PUB.exceptions.HorarioException;
import com.PUB_Online.PUB.exceptions.ObjectNotFoundException;
import com.PUB_Online.PUB.exceptions.PedidoException;
import com.PUB_Online.PUB.models.Pedido;
import com.PUB_Online.PUB.models.Pedido.Status;
import com.PUB_Online.PUB.repositories.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoService itemPedidoService;

    @Autowired
    private HorarioFuncionamentoService horarioFuncionamentoService;

    public Pedido create(List<ItemPedidoDTO> itens) {
        Pedido obj = new Pedido();
        if (!this.horarioFuncionamentoService.isHorarioFuncionamento()) {
            throw new HorarioException("Estamos Fora do horário de funcionamento");
        }
        obj.setId(null);
        obj.setItens(itens.stream().map(item -> this.itemPedidoService.fromDTO(item)).toList());
        obj.setPreco(new BigDecimal("0.00"));
        for (int i = 0; i < itens.size(); i++) {
            obj.getPreco().add(obj.getItens().get(i).getPrecoItemPedido());
        }
        obj.setStatus(Status.FILA);
        obj = this.pedidoRepository.save(obj);
        return obj;
    }

    public Pedido findById(Long id) {
        Optional<Pedido> obj = this.pedidoRepository.findById(id);
        if(obj.isPresent()) {
            return obj.get();
        } else {
            throw new ObjectNotFoundException("Pedido não encontrado");
        }
    }

    public Pedido updateStatus(Long id, Status status) {
        Pedido newObj = this.findById(id);
        newObj.setStatus(status);
        return this.pedidoRepository.save(newObj);
    }

    public void delete(Long id) {
        this.findById(id);
        if(!(this.findById(id).getStatus() == Status.FILA)) {
            throw new PedidoException("Não é possível deletar um pedido sendo preparado ou concluido");
        }
        this.pedidoRepository.deleteById(id);
    }

    public void deleteAdmin(Long id) {
        this.pedidoRepository.deleteById(id);
    }


}