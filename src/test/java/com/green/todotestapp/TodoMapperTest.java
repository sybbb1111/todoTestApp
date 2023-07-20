package com.green.todotestapp;

import com.green.todotestapp.model.TodoInsDto;
import com.green.todotestapp.model.TodoUpdDto;
import com.green.todotestapp.model.TodoVo;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TodoMapperTest {

    @Autowired
    private TodoMapper mapper;

    @Test
    void insTodo() {
        TodoInsDto p1 = new TodoInsDto();
        p1.setCtnt("테스트");
        p1.setPic("main.jpg");

        int r1 = mapper.insTodo(p1);

        assertEquals(1, r1);
        assertEquals(5, p1.getItodo());

        // ----------------------------

        TodoInsDto p2 = new TodoInsDto();
        p2.setCtnt("테스트2");

        int r2 = mapper.insTodo(p2);
        assertEquals(1, r2);
        assertEquals(6, p2.getItodo());

        List<TodoVo> list = mapper.selTodo();
        assertEquals(6,list.size());
        assertEquals("테스트", list.get(4).getCtnt());
        assertEquals("main.jpg", list.get(4).getPic());


    }

    @Test
    public void selTodo() {
        List<TodoVo> list = mapper.selTodo();
        assertEquals(4, list.size());

        TodoVo item1 = list.get(0);
        assertEquals(1, item1.getItodo());

        assertNotNull(item1.getCtnt());
        assertEquals("차돌", item1.getCtnt());

        assertNotNull(item1.getCreatedAt());
        assertEquals("2023-06-13T16:51:11", item1.getCreatedAt().toString());

        // ---------------

        TodoVo item2 = list.get(1);
        assertEquals("먹어", item2.getCtnt());

        assertNull(item1.getPic());
        assertEquals(0, item2.getFinishYn());

        assertNotNull(item2.getCreatedAt());
        assertNull(item2.getPic());
        assertNull(item2.getFinishedAt());
    }

    @Test
    public void updTodo() {

        TodoUpdDto dto1 = new TodoUpdDto();
        dto1.setItodo(1L);
        dto1.setCtnt("삼겹살");
        dto1.setPic("ddd.jpg");

        int r1 = mapper.updTodo(dto1);
        assertEquals(1, r1);

        TodoUpdDto dto2 = new TodoUpdDto();
        dto2.setItodo(2L);
        dto2.setCtnt("바꿧다");
        dto2.setPic(null);

        int r2 = mapper.updTodo(dto2);
        assertEquals(1, r2);

        List<TodoVo> list = mapper.selTodo();
        assertEquals(4, list.size());

        TodoVo item0 = list.get(0);
        assertEquals(dto1.getCtnt(), item0.getCtnt());




    }




}