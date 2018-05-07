package com.vironit.onlinevisacenter.dao.interfaces;

import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public interface ApplicationDAO {
    void save(Application application) throws EntitySaveException;
    void delete(Application application) throws EntityDeleteException;
    Application find(Integer key) throws EntityFindException;
    void update(Application application) throws EntityUpdateException;
    List<Application> findAll(Class<Application> classType) throws EntityFindException;
    List<Application> findApplicationsByClient(Integer userId) throws EntityFindException;
    void deleteById(Integer id) throws EntityDeleteException;

//    class ApplicationDAOFake implements ApplicationDAO{
//
//        @Override
//        public void save(Application application) throws EntitySaveException{
//
//        }
//
//        @Override
//        public void delete(Application application) throws EntityDeleteException{
//
//        }
//
//        @Override
//        public Application find(Integer key) throws EntityFindException {
//            return new Application();
//        }
//
//        @Override
//        public void update(Application application) throws EntityUpdateException{
//
//        }
//
//        @Override
//        public List<Application> findAll(Class<Application> classType) throws EntityFindException {
//            return new ArrayList<>();
//        }
//
//        @Override
//        public List<Application> findApplicationsByClient(User user) throws EntityFindException {
//            return new ArrayList<>();
//        }
//
//        @Override
//        public void deleteById(Integer id) throws EntityDeleteException {
//
//        }
//    }

}
