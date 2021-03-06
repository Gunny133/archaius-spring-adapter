package com.capgemini.archaius.spring.jdbc;

/*
 * Copyright (C) 2014 Capgemini (oss@capgemini.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.archaius.spring.jdbc.derby.AbstractArchaiusJdbcTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 * @author skumar81
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/jdbc/overLoadingPropertyFromFileWithJdbcTest.xml"})
@ActiveProfiles("default")
public class OverLoadingPropertyFromFileWithJdbcTest extends AbstractArchaiusJdbcTest {

    private final String overloadedPropertyArchaiusKey = "Error404";
    private final String expectedOverloadedArchaiusPropertyValue = "Page not found";

    private final String notOverloadedPropertyArchaiusKey = "Error405";
    private final String expectedNotOverloadedArchaiusPropertyValue = "This will not be overloaded by Jdbc - Method Not Allowed from Property file";

    @Test
    public void propertyOverloadingWorksWithJdbcPropertiesTest() throws InterruptedException {

        DynamicStringProperty prop2 
                = DynamicPropertyFactory.getInstance().getStringProperty(overloadedPropertyArchaiusKey, overloadedPropertyArchaiusKey);
        assertThat(prop2.get(), is(equalTo(expectedOverloadedArchaiusPropertyValue)));

        DynamicStringProperty prop4 
                = DynamicPropertyFactory.getInstance().getStringProperty(notOverloadedPropertyArchaiusKey, notOverloadedPropertyArchaiusKey);
        assertThat(prop4.get(), is(equalTo(expectedNotOverloadedArchaiusPropertyValue)));
    }
}
