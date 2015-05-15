package controllers;

import org.apache.commons.lang3.StringUtils;
import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
//        return ok(index.render("Your new application is ready."));
        return ok("Your brand-new application is ready.");
    }

    public static Result hello(final String name) {
        return ok(/*views.html.*/hello.render(StringUtils.isEmpty(name) ? "somebody" : name));
    }
}
