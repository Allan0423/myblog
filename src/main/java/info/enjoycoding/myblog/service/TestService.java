package info.enjoycoding.myblog.service;

import info.enjoycoding.myblog.mapper.TestMapper;
import info.enjoycoding.myblog.model.TestModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestService {

    private final TestMapper testDao;

    @Autowired
    public TestService(TestMapper dao) {
        this.testDao = dao;
    }

    public boolean insert(TestModel model) {
        return testDao.insert(model) > 0;
    }

    public TestModel select(int id) {
        return testDao.select(id);
    }

    public List<TestModel> selectAll() {
        return testDao.selectAll();
    }

    public boolean updateValue(TestModel model) {
        return testDao.updateValue(model) > 0;
    }

    public boolean delete(Integer id) {
        return testDao.delete(id) > 0;
    }
}
