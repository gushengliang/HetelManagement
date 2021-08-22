package org.jbit.entityTest;

import org.jbit.entity.Customer;
import org.junit.Test;

public class entityTest {
    @Test
    public void test() {
        Customer customer = new Customer("1000001", "jack", "123456", "17712345678");
        System.out.println(customer.toString());
    }
}
