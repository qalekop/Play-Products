import models.Product;
import org.junit.Test;
import play.data.Form;

import static org.fest.assertions.Assertions.*;

/**
 * Created by akopylov on 14.05.2015.
 */
public class ProductTest {

    private static final int EAN = 3;
    private static final String NAME = "Paperclips 3";
    private static final String FIELD_NAME = "name";

    @Test
    public void find() {
        Product product = Product.find(EAN);
        assertThat(product).isNotNull();
        assertThat(product.getName()).isEqualToIgnoringCase(NAME);
    }

    @Test
    public void fill() {
        Product product = Product.find(EAN);
        assertThat(product).isNotNull();
        assertThat(product.getName()).isEqualToIgnoringCase(NAME);

        Form<Product> form = Form.form(Product.class).fill(product);

//        assertThat(form.value().get().getName()).isEqualTo(NAME);
        assertThat(form.field(FIELD_NAME).value()).isEqualTo(NAME);
    }
}
