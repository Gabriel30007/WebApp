package ua.lviv.lgs.servlet;

import com.google.gson.Gson;
import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.dto.BucketDto;
import ua.lviv.lgs.service.BucketService;
import ua.lviv.lgs.service.ProductService;
import ua.lviv.lgs.service.impl.BucketServiceImpl;
import ua.lviv.lgs.service.impl.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "buckets", value = "/buckets")
public class BucketsController extends HttpServlet {
    private BucketService bucketService = BucketServiceImpl.getBucketService();
    private ProductService productService = ProductServiceImpl.getProductService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Bucket> buckets = bucketService.readAll();
        Map<Integer, Product> idToProduct = productService.readAllMap();
        List<BucketDto> listOfBucketDtos = map(buckets, idToProduct);

        String json = new Gson().toJson(listOfBucketDtos);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public List<BucketDto> map(List<Bucket> buckets, Map<Integer, Product> idToProduct){

        return	buckets.stream().map(bucket->{
            BucketDto bucketDto = new BucketDto();
            bucketDto.bucketId = bucket.getId();
            bucketDto.purchaseDate = bucket.getPurchaseDate();

            Product product = idToProduct.get(bucket.getProductId());
            bucketDto.name = product.getName();
            bucketDto.description = product.getDescription();
            bucketDto.price = product.getPrice();
            bucketDto.subscription=bucket.getSubscription();
            bucketDto.prepayment=bucket.getPrepayment();
            return bucketDto;
        }).collect(Collectors.toList());

    }
}
