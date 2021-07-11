package dubboprovider.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.ye.dubbo.dubboapi.pojo.User;
import com.ye.dubbo.dubboapi.pojo.serivce.UserSerivce;
import org.springframework.stereotype.Component;


@Component
@Service
public class UserServiceImpl implements UserSerivce {


    @Override
    public User getUser() {
        return null;
    }
}
