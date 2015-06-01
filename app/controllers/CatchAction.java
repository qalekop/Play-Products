package controllers;

import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

/**
 * Created by akopylov on 28.05.2015.
 */
public class CatchAction extends Action.Simple {
    @Override
    public F.Promise<Result> call(Http.Context context) throws Throwable {
        try {
            return delegate.call(context);
        } catch (Throwable t) {
            System.out.println(String.format("*** Oops! Something weird happened: %s", t.getLocalizedMessage()));
            throw new RuntimeException(t);
        }
    }
}
