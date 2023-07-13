package com.intheeast.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReflectionTest {
    @Test
    public void invokeMethod() throws Exception {
        String name = "Spring";

        //length()
        assertEquals(name.length(), 6);

        // String class 내부에 length란 이름의 method 정보들을 가져옴.
        // 실행이 필요할 시 Method 클래스의 invoke method 호출
        // => 메소드를 실행시킬 대상 오브젝트와 파라미터 목록을 받아서
        // 메소드를 호출하고 그 결과를 Object 타입으로 돌려준다.
        Method lengthMethod = String.class.getMethod("length");
        
        
        assertEquals((Integer)lengthMethod.invoke(name), 6);
    }
}
