package order;

import order.Model.dao.SalesHeadDao;
import order.Model.dto.SalesHeadDto;
import order.Model.dao.SalesLineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private SalesHeadDao hDao;
    private SalesLineDao lDao;

    @Transactional
    public void executeOrder(OrderForm form) {

        SalesHeadDto hDto = new SalesHeadDto();

    }
}
