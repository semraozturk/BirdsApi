package pojo.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import java.util.List;


@NoArgsConstructor
@ToString
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private String title;
    private String body_html;
    private String vendor;
    private String product_type;
    private List<String> tags;



    /*

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody_html() {
        return body_html;
    }

    public void setBody_html(String body_html) {
        this.body_html = body_html;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getMetafields_global_title_tag() {
        return metafields_global_title_tag;
    }

    public void setMetafields_global_title_tag(String metafields_global_title_tag) {
        this.metafields_global_title_tag = metafields_global_title_tag;
    }

    public String getMetafields_global_description_tag() {
        return metafields_global_description_tag;
    }

    public void setMetafields_global_description_tag(String metafields_global_description_tag) {
        this.metafields_global_description_tag = metafields_global_description_tag;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", body_html='" + body_html + '\'' +
                ", vendor='" + vendor + '\'' +
                ", product_type='" + product_type + '\'' +
                ", metafields_global_title_tag='" + metafields_global_title_tag + '\'' +
                ", metafields_global_description_tag='" + metafields_global_description_tag + '\'' +
                '}';
    }

*/

}


