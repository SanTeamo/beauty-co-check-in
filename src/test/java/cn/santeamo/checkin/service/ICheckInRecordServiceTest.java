package cn.santeamo.checkin.service;

import cn.santeamo.checkin.entity.CheckInRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ICheckInRecordServiceTest {

    @Autowired
    private ICheckInRecordService service;

    @Test
    public void list() {
        List<CheckInRecord> list = service.list();
        System.out.println(list);
    }
}
