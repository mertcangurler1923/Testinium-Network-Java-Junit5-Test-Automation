import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class NetworkTest extends BaseTest{
    @Test
    @Order(0)
    public void networkTest() throws FileNotFoundException {
        NetworkPage networkPage=new NetworkPage(driver);
        List<String> data=networkPage.test();
        String[] tlDiscount= data.get(3).split(",");
        String[] tlDisNotcount= data.get(6).split(",");
        double discount= Double.parseDouble(tlDiscount[0]);
        double disNotcount=Double.parseDouble(tlDisNotcount[0]);
        Assertions.assertEquals("https://www.network.com.tr/",data.get(0));
        Assertions.assertNotEquals(data.get(0),data.get(1));
        Assertions.assertEquals(data.get(2),data.get(3));
        Assertions.assertEquals(data.get(4),"size_"+data.get(5));
        Assertions.assertTrue( discount < disNotcount);
        Assertions.assertEquals(data.get(7),"true");
        Assertions.assertEquals(data.get(8),"Sepetiniz Henüz Boş");
    }
}
