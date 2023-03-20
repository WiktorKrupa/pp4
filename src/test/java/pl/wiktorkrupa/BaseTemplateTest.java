package pl.wiktorkrupa;

import org.junit.jupiter.api.Test;

public class BaseTemplateTest {

    @Test
    void testIt() {
        assert true == true;
    }

    @Test
    void testIt2(){
        String myName = "Wiktor";
        String output = String.format("Hello %s", myName);

        assert output.equals("Hello Wiktor");

    }

    @Test
    void baseSchema() {
        //Arrange   //Given     //Input
        //Act       //When      //interaction
        //Assert    //Then      //output

    }
}
