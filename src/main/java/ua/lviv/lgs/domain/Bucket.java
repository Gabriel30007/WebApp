package ua.lviv.lgs.domain;

import java.util.Date;
import java.util.Objects;

public class Bucket {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Date purchaseDate;
    private Boolean subscription;
    private Double prepayment;

    public Bucket(Integer id, Integer userId, Integer productId, Date purchaseDate,Boolean subscription,Double prepayment) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.purchaseDate = purchaseDate;
        this.subscription=subscription;
        this.prepayment=prepayment;
    }

    public Bucket(Integer userId, Integer productId, Date purchaseDate,Boolean subscription,Double prepayment) {
        this.userId = userId;
        this.productId = productId;
        this.purchaseDate = purchaseDate;
        this.subscription=subscription;
        this.prepayment=prepayment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Boolean getSubscription() {

        return subscription;
    }

    public void setSubscription(Boolean subscription) {

        this.subscription = subscription;
    }
    public Double getPrepayment() {
        return prepayment;
    }

    public void setPrepayment(Double prepayment) {
        this.prepayment = prepayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bucket bucket = (Bucket) o;
        return id.equals(bucket.id) && userId.equals(bucket.userId) && productId.equals(bucket.productId) && purchaseDate.equals(bucket.purchaseDate) && subscription.equals(bucket.subscription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, productId, purchaseDate, subscription);
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", purchaseDate=" + purchaseDate +
                ", subscription=" + subscription +
                '}';
    }
}
