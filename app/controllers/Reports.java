package controllers;

import models.Report;
import play.libs.F;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import play.Logger;
import views.html.reports.report;
import views.html.reports.reports;

/**
 * Created by akopylov on 04.06.2015.
 */
public class Reports extends Controller {

    private static final String REPORT_LINK = "<li><b>%s</b> ready to be downloaded <a href=\"javascript:void(0);\">here</a></li>";

    public static Promise<Result> index() {
        return Promise.sequence(Promise.promise(Reports::intensiveKPIReport)
                , Promise.promise(Reports::intensiveETAReport))
                .map(reports -> ok(report.render(reports)));
    }

    public static Result start() {
        return ok(reports.render());
    }

    public static WebSocket<String> updateReports() {
        return new WebSocket<String>() {
            @Override
            public void onReady(In<String> in, Out<String> out) {
                Promise intensiveKPIReport = Promise.promise(Reports::intensiveKPIReport);
                Promise intensiveETAReport = Promise.promise(Reports::intensiveETAReport);
                intensiveKPIReport.onRedeem(report -> out.write(buildReportLink((Report) report)));
                intensiveETAReport.onRedeem(report -> out.write(buildReportLink((Report) report)));
                Promise.sequence(intensiveKPIReport, intensiveETAReport).onRedeem(reports -> out.close());

                in.onMessage(event -> Logger.info("*** onMessage:" + event + "."));
                in.onClose(() -> Logger.info("Disconnected"));
            }
        };
    }

    private static String buildReportLink(Report report) {
        return String.format(REPORT_LINK, report.toString());
    }

    private static Report intensiveKPIReport() {
        Report r = new Report("KPI report");
        r.execute();
        return r;
    }

    private static Report intensiveETAReport() {
        Report r = new Report("ETA report", (short) 10);
        r.execute();
        return r;
    }
}
