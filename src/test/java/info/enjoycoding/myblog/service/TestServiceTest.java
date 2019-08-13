package info.enjoycoding.myblog.service;

import info.enjoycoding.myblog.model.TestModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServiceTest {

    @Autowired
    private TestService service;

    @Test
    public void contextLoads(){
        TestModel model = new TestModel();
        model.setKey("测试Key1");
        model.setValue("测试Value1");

        List<TestModel> all = service.selectAll();
        int size = all.size();

        boolean insertResult = service.insert(model);
        Assert.assertTrue(insertResult);

        System.out.println("ModelId: " + model.getId());
        TestModel selectModel = service.select(model.getId());

        all = service.selectAll();
        Assert.assertEquals(size + 1, all.size());

        selectModel.setValue("测试更新Value1");
        boolean updateResult = service.updateValue(selectModel);
        Assert.assertTrue(updateResult);

        boolean deleteResult = service.delete(selectModel.getId());
        Assert.assertTrue(deleteResult);
    }
}