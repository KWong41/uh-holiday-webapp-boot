package edu.hawaii.its.holiday.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.hawaii.its.holiday.configuration.SpringBootWebApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { SpringBootWebApplication.class })
public class AdministratorServiceTest {

    @Autowired
    private AdministratorService administratorService;

    @Test
    public void exists() {
        assertTrue(administratorService.exists("89999999"));
        assertTrue(administratorService.exists("10000001"));

        assertFalse(administratorService.exists("10000008"));
        assertFalse(administratorService.exists(null));
        assertFalse(administratorService.exists(""));
        assertFalse(administratorService.exists("  "));
        assertFalse(administratorService.exists("no-way-none"));

        List<String> admins = null;
        administratorService.load(admins);
        assertFalse(administratorService.exists("10000001"));
        assertNull(admins);

        admins = new ArrayList<String>();
        administratorService.load(admins);
        assertFalse(administratorService.exists("10000001"));

        admins.add("10000001");
        administratorService.load(admins);
        assertTrue(administratorService.exists("10000001"));
    }

    @Test
    public void testBrokenConfig() {
        AdministratorService administratorService = new AdministratorServiceImpl();

        assertFalse(administratorService.exists("10000001"));
        assertFalse(administratorService.exists("10000002"));

        assertFalse(administratorService.exists(null));
        assertFalse(administratorService.exists(""));
        assertFalse(administratorService.exists("  "));
        assertFalse(administratorService.exists("no-way-none"));
    }
}
