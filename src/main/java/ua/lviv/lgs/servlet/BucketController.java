package ua.lviv.lgs.servlet;

import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.service.BucketService;
import ua.lviv.lgs.service.impl.BucketServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;

import static ua.lviv.lgs.servlet.ProductController.getValidatedPrice;

@WebServlet(name = "Bucket", value = "/Bucket")
public class BucketController extends HttpServlet {

    private BucketService bucketService = BucketServiceImpl.getBucketService();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        String subscriptions= request.getParameter("subscription");
        boolean subscription=false;
        if(subscriptions.equals("true")){
             subscription=true;
        }
        String prepayments= request.getParameter("prepayment");
        double prepayment=getValidatedPrice(prepayments);
        HttpSession session = request.getSession();
        Integer userId = (Integer)session.getAttribute("userId");


        Bucket bucket = new Bucket(userId, Integer.parseInt(productId), new Date(),subscription, prepayment);
        bucketService.create(bucket);


        response.setContentType("text/javascript");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Success");
    }
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bucketId = request.getParameter("bucketId");
        bucketService.delete(Integer.parseInt(bucketId));
        response.setContentType("text");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Success");
    }
}
