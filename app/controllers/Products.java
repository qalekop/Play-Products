package controllers;

import models.Product;
import models.ProductTypeEnum;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import views.html.products.details;
import views.html.products.list;

/**
 * Created by akopylov on 13.05.2015.
 */
public class Products extends Controller {

    private static final Form<Product> productForm = Form.form(Product.class);

    public static Result index() {
        return redirect(controllers.routes.Products.list(0));
    }

    public static Result list(Integer page) {
        return ok(list.render(Product.findAll()));
    }

    public static Result newProduct() {
        System.out.println("-- new product called --");
        return ok(details.render(productForm, ProductTypeEnum.values(), new Product()));
    }

    public static Result details(int ean) {
        System.out.println("-- details for ean=" + ean);
        final Product product = Product.find(ean);
        if (product == null) {
            return notFound(String.format("Product %s does not exist.", ean));
        }
        System.out.println("-- found product name=" + product.getName());
        return ok(details.render(Form.form(Product.class).fill(product), ProductTypeEnum.values(), product));
    }

    public static Result details(Product product) {
        System.out.println("-- details for name=" + product.getName());
        System.out.println("-- found product ean=" + product.getEan());
        return ok(details.render(Form.form(Product.class).fill(product), ProductTypeEnum.values(), product));
    }

    public static Result save() {
        System.out.println("-- save product called --");
        Form<Product> boundForm = productForm.bindFromRequest();
        if(boundForm.hasErrors()) {
            flash("error", "Please correct the form below.");
            return badRequest(details.render(boundForm, ProductTypeEnum.values(), new Product()));
        }
        Product product = boundForm.get();
        product.save();
        flash("success", String.format("Successfully added product %s", product));
        return redirect(controllers.routes.Products.list(1));
    }

    @With(CatchAction.class)
    public static Result delete(int ean) {
        final Product product = Product.find(ean);
        if(product == null) {
            return notFound(String.format("Product %s does not exists.", ean));
        }
        Product.remove(product);
        return redirect(controllers.routes.Products.list(0));
    }
}
