import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class NetworkPage extends BasePage{
    By cookie= By.id("onetrust-accept-btn-handler");
    By searchTextbox= By.cssSelector("form[id='search-form'] input[id='search']");
    By allPercentList=By.cssSelector("div[id*='product-1']");
    By discountPercent=By.cssSelector("div [class='product__discountPercent']");
    By size=By.cssSelector("div[class='product__size -productCart radio-box'] label[class='radio-box__label']");
    By goToCard=By.cssSelector("a[class*='button -primary header_']");
    By boxDiscountMoney=By.cssSelector("span[class='cartItem__price -sale']");
    By boxNotDiscountMoney=By.cssSelector("span[class='cartItem__price -old -labelPrice']");
    By boxSize=By.cssSelector("div[class='cartItem__attr -size'] span[class='cartItem__attrValue']");
    By showMore=By.cssSelector("button[class='button -secondary -sm relative']");
    By mailBox=By.cssSelector("input[class='input js-trim']");
    By passwordBox=By.cssSelector("input[class='input']");
    By login=By.cssSelector("button[type='submit']");
    By goOn=By.cssSelector("div[class='summary'] button[type='button']");
    By network=By.cssSelector("svg[class='img-fluid']");
    By box=By.cssSelector("button > svg[class='header__icon -shoppingBag']");
    By boxTrash=By.cssSelector("svg[class='header__basketProductRemove'] > use");
    By boxTrashYes=By.cssSelector("button[class='btn -black o-removeCartModal__button']");
    By boxEmpty=By.cssSelector("span[class='header__emptyBasketText']");
    Log log = new Log();
    public NetworkPage(WebDriver driver) {
        super(driver);
    }
    public List<String> test() throws FileNotFoundException {
        List<String> data=new ArrayList<String>();
        data.add(getlocation());
        cookie(cookie);
        log.info("Cookie kapatıldı.");
        inputEnter(searchTextbox,"ceket");
        log.info("Textboxa ceket ürünü girildi.");
        scrollToPage(showMore);
        click(showMore);
        log.info("İkinci sayfaya gidildi.");
        List<List<By>> discountPercentCard=discountPercentLocatorFind(allPercentList);
        data.add(getlocation());
        scrollToPage(discountPercentCard.get(0).get(0));
        String afterSizeValue=getAttribute(discountPercentCard.get(1).get(0),"for");
        String afterMoneyValue=getText(discountPercentCard.get(2).get(0));
        moveToElement(discountPercentCard.get(0).get(0));
        scrollToPage(discountPercentCard.get(1).get(0));
        click(discountPercentCard.get(1).get(0));
        log.info("Beden seçildi.");
        click(goToCard);
        log.info("Sepete gidildi.");
        String boxSizeValue=getText(boxSize);
        String boxDiscountMoneyValue=getText(boxDiscountMoney);
        String boxNotDiscountMoneyValue=getText(boxNotDiscountMoney);
        data.add(afterMoneyValue);
        data.add(boxDiscountMoneyValue);
        data.add(afterSizeValue);
        data.add(boxSizeValue);
        data.add(boxNotDiscountMoneyValue);
        List<String> idPassword=csvRead();
        click(goOn);
        log.info("Devam et butonuna tıklanıldı.");
        input(mailBox,idPassword.get(0));
        input(passwordBox,idPassword.get(1));
        data.add(wait(login));
        click(login);
        log.info("Mail ve şifre ile login olundu.");
        click(network);
        log.info("Network'a tıklanıldı.");
        click(box);
        log.info("Sepete tıklanıldı.");
        click(boxTrash);
        log.info("Sepetten ürün silindi");
        click(boxTrashYes);
        log.info("'SEPETİMDEN ÇIKAR' sorusuna çıkart denildi.");
        click(box);
        sleep(2000);
        String boxEmptyValue=getText(boxEmpty);
        data.add(boxEmptyValue);
        log.info("Test bitti.");
        return data;
    }
}
