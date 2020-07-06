package org.xi.test;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class Clue {

    private long id1;
    private Long id;
    Long clueOriginId;

    Long clueOriginTagId;


    //-新增字段(请勿修改)-----------------------------------------------------------------------------------
    Integer process_state;
    String orign_shop_code;
    String orign_second_shop_code;
    Integer clue_rate;
    Integer clue_state; //CRM线索状态：1：新建、2：回访中、3：战败申请、4：失控申请、5：战败、6：失控、7：已预约、8：已到店、9：新分配、10：驳回、11：邀约失败、12：撞单无效、13：无效申请、14：无效
    long customer_id;
    String crm_mobile;
    Integer push_flag;
    Integer push_count;
    long repeat_clue_id;
    long repeat_customer_id;
    Integer source_type;//废弃字段
    String tag_url;
    /**
     * 重复类型
     */
    Integer repeat_type = 0;
    //------------------------------------------------------------------------------------

    @JSONField(name = "Contact")
    String contact;

    @JSONField(name = "Name")
    String name;

    @JSONField(name = "Age")
    String age;

    @JSONField(name = "ShopID")
    Integer shopId;

    @JSONField(name = "Shnumber")
    String shopCode;

    @JSONField(name = "DealerID")
    String dealerId;

    @JSONField(name = "TypeName")
    String typeName;

    @JSONField(name = "Channels")
    String channel;

    @JSONField(name = "ChildChannel")
    String subChannel;

    @JSONField(name = "ForthChannel")
    String forthChannel;

    @JSONField(name = "FifthChannel")
    String fifthChannel;

    @JSONField(name = "LandPage1")
    String landPage1;

    @JSONField(name = "LandPage2")
    String landPage2;

    @JSONField(name = "LandPage3")
    String landPage3;

    @JSONField(name = "ClueType")
    Integer clueType;

    @JSONField(name = "ActivityCode")
    String activityCode;

    @JSONField(name = "ActivityType")
    String activityType;

    @JSONField(name = "ActivitiesName")
    String activityInfo;

    @JSONField(name = "ActivitiesDuration")
    String activityDesc;

    @JSONField(name = "WebLocation")
    String webLocation;

    @JSONField(name = "Character")
    String customCharacter;

    @JSONField(name = "BrandName")
    String brandName;

    @JSONField(name = "IntentBrandID")
    Integer intentBrandId;

    @JSONField(name = "IntentBrandName")
    String intentBrandName;

    @JSONField(name = "CarType")
    String carType;

    @JSONField(name = "Province")
    String province;

    @JSONField(name = "City")
    String city;

    @JSONField(name = "Address")
    String address;

    @JSONField(name = "IsTerminal")
    String isTerminal;

    @JSONField(name = "OpportunitiyID")
    String oppId;

    @JSONField(name = "OpportunitiyOrderType")
    String oppType;

    @JSONField(name = "OpportunitiyRemark")
    String oppRemark;

    @JSONField(name = "OpportunitiyCreateTime")
    Date oppCreateTime;

    @JSONField(name = "WeiXinOpenID")
    String wechatOpenId;

    @JSONField(name = "WeiXinNiCheng")
    String wechatNickname;

    @JSONField(name = "WeiXinSuggestConsultant")
    String wechatConsultant;

    @JSONField(name = "WeiXinNo")
    String wechatNo;

    @JSONField(name = "Customerfocus")
    String customerFocus;

    @JSONField(name = "Pattern")
    String carStyle;

    @JSONField(name = "Color")
    String carColor;

    @JSONField(name = "EngineType")
    String engineCode;

    @JSONField(name = "Configuration")
    String configuration;

    @JSONField(name = "Option")
    String configOption;

    @JSONField(name = "PayType")
    String payType;

    @JSONField(name = "PayInAdvance")
    String payInAdvance;

    @JSONField(name = "SaleClueInfoID")
    String mallId;

    @JSONField(name = "utm")
    String utm;

    @JSONField(name = "IsPublic")
    Integer isPublic;

    @JSONField(name = "Gender")
    Integer gender;

    @JSONField(name = "Email")
    String email;

    @JSONField(name = "BuyCarIndex")
    String buyCarIndex;

    @JSONField(name = "AttentionSpecs")
    String attentionSpecs;

    @JSONField(name = "Budget")
    String budget;

    @JSONField(name = "ReplaceProbability")
    String replaceProbability;

    @JSONField(name = "LoanProbability")
    String loanProbability;

    @JSONField(name = "ConfigurePrefer")
    String configurePrefer;

    @JSONField(name = "OnlinePrefer")
    String onlinePrefer;

    @JSONField(name = "CustomerCity")
    String customerCity;

    @JSONField(name = "Purpose")
    String purpose;

    @JSONField(name = "UserPhone")
    String userPhone;

    @JSONField(name = "OpeningRemarks")
    String openingRemarks;

    @JSONField(name = "SpecIntroduction")
    String specIntroduction;

    @JSONField(name = "ActivityRecommend")
    String activityRecommend;

    @JSONField(name = "PricingStrategy")
    String pricingStrategy;

    @JSONField(name = "InviteToShop")
    String inviteToShop;

    @JSONField(name = "ZhwxUrl")
    String zhwlUrl;

    @JSONField(name = "Terminal")
    String terminal;

    @JSONField(name = "AccessPeriod")
    String accessPeriod;

    @JSONField(name = "AgeRange")
    String ageRange;

    @JSONField(name = "Ifowncar")
    Integer ifOwnCar;

    @JSONField(name = "operator")
    String operator;

    @JSONField(name = "SerialList")
    String serialList;

    /**
     * 纯字符串格式，不需要校验
     */
    @JSONField(name = "PurchaseDate")
    String purchaseDate;

    /**
     * 创建人
     * <p>
     * 线索采集接口，默认为 sys
     */
    @JSONField(name = "Author")
    String author = "sys";

    /**
     * 贷款意向, 1-有， 0-无
     */
    @JSONField(name = "LoanPurpose")
    Integer loanPurpose;

    @JSONField(name = "LoanProduct")
    String loanProduct;

    @JSONField(name = "LoanPaymentRatio")
    String loanPaymentRatio;

    @JSONField(name = "LoanAmt")
    String loanAmt;

    @JSONField(name = "LoanPeriods")
    String loanPeriods;

    @JSONField(name = "LoanInterest")
    String loanInterest;

    /**
     * 是否核销， 1-是，0-否
     */
    @JSONField(name = "IsOff")
    Integer isOff;
}