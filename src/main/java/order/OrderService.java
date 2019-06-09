package order;

import order.Model.SalesHeadDao;
import order.Model.SalesLineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    SalesHeadDao hDao;
    SalesLineDao lDao;

    @Transactional
    public void executeOrder(){

    }
}
