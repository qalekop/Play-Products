package controllers;

import models.Report;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.products.report;

/**
 * Created by akopylov on 04.06.2015.
 */
public class Reports extends Controller {

    public static Promise<Result> index() {
        Promise<Report> promiseOfKPIReport = Promise.promise(Reports::intensiveKPIReport);
        Promise<Report> promiseOfETAReport = Promise.promise(Reports::intensiveETAReport);

        return Promise.sequence(promiseOfKPIReport, promiseOfETAReport).map(reports -> ok(report.render(reports)));
    }

    public static Report intensiveKPIReport() {
        Report r = new Report("KPI report");
        r.execute();
        return r;
    }

    public static Report intensiveETAReport() {
        Report r = new Report("ETA report", (short) 10);
        r.execute();
        return r;
    }
}
