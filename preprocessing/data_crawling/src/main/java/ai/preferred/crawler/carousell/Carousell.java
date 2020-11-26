package ai.preferred.crawler.carousell;

import ai.preferred.venom.Handler;
import ai.preferred.venom.Session;
import ai.preferred.venom.Crawler;
import ai.preferred.venom.Worker;
import ai.preferred.venom.job.Scheduler;
import ai.preferred.venom.request.Request;
import ai.preferred.venom.request.VRequest;
import ai.preferred.venom.response.VResponse;

public class Carousell {

    private static class VenomHandler implements Handler {

        @Override
        public void handle(Request request,
                           VResponse response,
                           Scheduler scheduler,
                           Session session,
                           Worker worker) {

            String about = response.getJsoup().select("._1gJzwc_bJS._2NNa9Zomqk.Rmplp6XJNu.mT74Grr7MA.nCFolhPlNA.lqg5eVwdBz.uxIDPd3H13._30RANjWDIv").text();
            String[] users = about.split(" ");
            for (String user : users) {
                System.out.println(user);
            }

        }

    }

    public static void main(
            String[] args) throws Exception {
        try (Crawler c = Crawler.buildDefault().start()) {
            Request r = new VRequest("https://www.carousell.sg/categories/mobile-phones-215/?sc=1202081422120a0c74696d655f63726561746564120208002a140a0b636f6c6c656374696f6e7322050a033231353a0408bbe17242037765624a02656e&sort_by=time_created%2Cdescending");
            c.getScheduler().add(r, new VenomHandler());
        }
    }

}

