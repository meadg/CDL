package com.cdl.application;

import com.cdl.command.CheckOutApplicationCommand;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CheckOutSessionTest {

    @Mock
    private CheckOutApplicationCommand checkOutApplicationCommand;

    private CheckOutSession checkOutSession1,checkOutSession2;

    @Before
    public void setUp(){
        checkOutSession1 = new CheckOutSession();
        checkOutSession2 = new CheckOutSession();

    }


    @Test
    public void setStateWorksCorrectly(){

        assertThat(checkOutSession1, is(checkOutSession2));
        checkOutSession2.setState(SessionState.SCANNING);
        assertThat(checkOutSession1, is(not(checkOutSession2)));

    }

    @Test
    public void executesCommand(){
        checkOutSession1.handleApplicationCommand(checkOutApplicationCommand);
        verify(checkOutApplicationCommand).executeCommand(checkOutSession1);
    }

}