package com.cf.sqlTest.api.designPatterns.flyWeightMode;

/**
 * @author: lpy
 * @Date: 2023/11/06
 */
public class FlyWeightTest {
    public static void main(String[] args) {
        Website blog = WebsiteFactory.registerWebsite("博客");
        Website mail = WebsiteFactory.registerWebsite("商户");
        blog.use(new User().setId(1).setName("小明"));
        blog.use(new User().setId(2).setName("费米"));
        blog.use(new User().setId(3).setName("奥本海默"));
        mail.use(new User().setId(4).setName("小蝉"));

        System.out.println(WebsiteFactory.getCount());

    }
}
