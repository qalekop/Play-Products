package controllers;

import models.Product;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.products.details;
import views.html.products.list;

/**
 * Created by akopylov on 13.05.2015.
 */
public class Products extends Controller {

    private static final Form<Product> productForm = Form.form(Product.class);

    public static Result list() {
        return ok(list.render(Product.findAll()));
    }

    public static Result newProduct() {
        System.out.println("-- new product called --");
        return ok(details.render(productForm));
    }

    public static Result details(int ean) {
        System.out.println("-- details for ean=" + ean);
        final Product product = Product.find(ean);
        if (product == null) {
            return notFound(String.format("Product %s does not exist.", ean));
        }
        System.out.println("-- found product name=" + product.getName());
        return ok(details.render(Form.form(Product.class).fill(product)));
    }

    public static Result save() {
        System.out.println("-- save product called --");
        Form<Product> boundForm = productForm.bindFromRequest();
        if(boundForm.hasErrors()) {
            flash("error", "Please correct the form below.");
            return badRequest(details.render(boundForm));
        }
        Product product = boundForm.get();
        product.save();
        flash("success", String.format("Successfully added product %s", product));
        return redirect(routes.Products.list());
    }
}
