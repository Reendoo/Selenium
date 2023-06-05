package sk.peterrendek.advanced.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.IncludeTags;
import sk.peterrendek.advanced.base.TestBase;
import sk.peterrendek.advanced.enums.SinType;
import sk.peterrendek.advanced.model.Sin;
import sk.peterrendek.advanced.pages.SinCityPage;
import sk.peterrendek.advanced.pages.SpartaPage;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Sincity")
@Tag("Blbosti")
public class SinCityTest extends TestBase {

    @Tag("Priority")
    @Test
    void forgivenTest() {
        SinCityPage page = new SinCityPage(getDriver());
        Sin sin = new Sin("Preliala, rozbila a neolutovala...",
                "Casnicka",
                "Bratislava 30.02.2022, Casnicka preliala Diplamatico ... a este sa tomu smiala");
        sin.addTag(SinType.BLACKMAIL);
        sin.addTag(SinType.HIJACK);
        sin.addTag(SinType.MURDER);
        sin.addTag(SinType.ROBBERY);
        sin.addTag(SinType.CAR_ACCIDENT);
        page.openPage();
        page.eraseSins();
        page.filSinInformation(sin);
        page.confessSin();
        page.openSinDetail(sin);
        page.isForgiven(sin);
        assertEquals(sin.isForgiven(), page.isForgiven(sin), "Shin shouldn't be forgiven");
    }


    @Tag("Priority")
    @Test
    void detailOfSinTest() {
        SinCityPage page = new SinCityPage(getDriver());
        Sin sin = new Sin("Preliala, rozbila a neolutovala...",
                "Casnicka",
                "Bratislava 30.02.2022, Cascnicka preliala Diplamatico ... a este sa tomu smiala");
        sin.addTag(SinType.BLACKMAIL);
        sin.addTag(SinType.HIJACK);
        sin.addTag(SinType.MURDER);
        sin.addTag(SinType.ROBBERY);
        sin.addTag(SinType.CAR_ACCIDENT);
        page.openPage();
        page.eraseSins();
        page.filSinInformation(sin);
        page.confessSin();
        page.openSinDetail(sin);
        Sin sinFromPage = page.getRequiredSinFromPage(sin);
        assertAll(() -> {
                    assertTrue(sinFromPage.getTitle().contains(sin.getTitle()), "Tittle should contains");
                    assertEquals(sin.getAuthor(), sinFromPage.getAuthor(), "Author should be same");
                    assertTrue(sinFromPage.getMessage().contains(sin.getMessage()), "Message should contains");
                    assertTrue((sin.getTags().size() == sinFromPage.getTags().size())
                            && (sinFromPage.getTags().containsAll(sin.getTags())), "Tags should be same");
                }
        );
    }

    @Tag("Priority")
    @Test
    void forgivenActionTest() {
        List<Sin> sins = new ArrayList<>();
        prepareSins(sins);
        SinCityPage sinCityPage = new SinCityPage(getDriver());
        sinCityPage.openPage();
        sinCityPage.eraseSins();
        sins.forEach(sin ->{
            sinCityPage.filSinInformation(sin);
            sinCityPage.confessSin();
        } );

        Sin actualSin = sins.get(0);
        SpartaPage spartaPage = new SpartaPage(getDriver());
        spartaPage.openPage();
        spartaPage.forgiveSin(spartaPage.findArticle(actualSin.forgive()));

        Exception e = assertThrows(RuntimeException.class,() -> spartaPage.findArticle(actualSin));
        assertEquals("Article doesn't exists",e.getMessage());

        sinCityPage.openPage();
        sinCityPage.openSinDetail(actualSin);
        sinCityPage.isForgiven(actualSin);
        assertEquals(actualSin.isForgiven(), sinCityPage.isForgiven(actualSin), "Sin shouldn be forgiven");
    }

    private void prepareSins(List<Sin> sins) {
        for (int i = 1; i <= 6; i++) {
            Sin sin = new Sin("[" + i + "]Preliala, rozbila a neolutovala...",
                    "[" + i + "]Casnicka",
                    "[" + i + "]Bratislava 30.02.2022, Cascnicka preliala Diplamatico ... a este sa tomu smiala");
            prepareTags(i, sin);
            sins.add(sin);
        }
    }

    private static void prepareTags(int i, Sin sin) {
        switch (i) {
            case 1 -> {
                sin.addTag(SinType.BLACKMAIL);
                sin.addTag(SinType.HIJACK);
                sin.addTag(SinType.MURDER);
                sin.addTag(SinType.ROBBERY);
                sin.addTag(SinType.CAR_ACCIDENT);
            }
            case 2 -> {
                sin.addTag(SinType.BLACKMAIL);
                sin.addTag(SinType.HIJACK);
                sin.addTag(SinType.MURDER);
                sin.addTag(SinType.ROBBERY);
            }
            case 3 -> {
                sin.addTag(SinType.BLACKMAIL);
                sin.addTag(SinType.HIJACK);
                sin.addTag(SinType.MURDER);
            }
            case 4 -> {
                sin.addTag(SinType.BLACKMAIL);
                sin.addTag(SinType.HIJACK);
            }
            case 5 -> sin.addTag(SinType.BLACKMAIL);
            default -> sin.addTag(SinType.NO_TAGS);
        }
    }
}
