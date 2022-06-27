package com.dliberty.cms;

import java.util.Date;

import com.dliberty.cms.entity.CmsMenuCategoryEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dliberty.cms.service.CmsMenuCategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MenuApplication.class)
public class CategoryTest {
	
	@Autowired
	private CmsMenuCategoryService cmsMenuCategoryService;
	
	@Test
	public void selectCategory() {
		String cate = "{\n" +
	            "  \"showapi_res_code\": 0,\n" +
	            "  \"showapi_res_error\": \"\",\n" +
	            "  \"showapi_res_body\": {\n" +
	            "    \"汤粥主食\": {\n" +
	            "      \"饼\": [\n" +
	            "        \"饼\"\n" +
	            "      ],\n" +
	            "      \"饭\": [\n" +
	            "        \"饭\",\n" +
	            "        \"焖\",\n" +
	            "        \"饭团\",\n" +
	            "        \"盖浇饭\",\n" +
	            "        \"煲仔饭\",\n" +
	            "        \"焗饭\",\n" +
	            "        \"烩饭\"\n" +
	            "      ],\n" +
	            "      \"汤羹\": [\n" +
	            "        \"汤羹\"\n" +
	            "      ],\n" +
	            "      \"面\": [\n" +
	            "        \"面\",\n" +
	            "        \"炒面\",\n" +
	            "        \"汤面\",\n" +
	            "        \"凉面\",\n" +
	            "        \"焖面\"\n" +
	            "      ]\n" +
	            "    },\n" +
	            "    \"蛋奶豆制品\": {\n" +
	            "      \"蛋类\": [\n" +
	            "        \"鸡蛋\",\n" +
	            "        \"咸蛋\",\n" +
	            "        \"皮蛋\",\n" +
	            "        \"咸蛋黄\",\n" +
	            "        \"鹌鹑蛋\",\n" +
	            "        \"土鸡蛋\",\n" +
	            "        \"鸭蛋\"\n" +
	            "      ],\n" +
	            "      \"奶制品\": [\n" +
	            "        \"牛奶\",\n" +
	            "        \"黄油\",\n" +
	            "        \"巧克力\",\n" +
	            "        \"奶酪\",\n" +
	            "        \"酸奶\",\n" +
	            "        \"无盐黄油\",\n" +
	            "        \"黑巧克力\",\n" +
	            "        \"淡奶油\",\n" +
	            "        \"奶油奶酪\",\n" +
	            "        \"炼乳\",\n" +
	            "        \"奶粉\",\n" +
	            "        \"马苏里拉...\",\n" +
	            "        \"白巧克力\",\n" +
	            "        \"酸奶油\",\n" +
	            "        \"酥油\"\n" +
	            "      ],\n" +
	            "      \"豆制品\": [\n" +
	            "        \"豆腐\",\n" +
	            "        \"香干\",\n" +
	            "        \"豆渣\",\n" +
	            "        \"千张\",\n" +
	            "        \"北豆腐\",\n" +
	            "        \"腐竹\",\n" +
	            "        \"素鸡\",\n" +
	            "        \"油豆皮\"\n" +
	            "      ]\n" +
	            "    },\n" +
	            "    \"蔬菜水果\": {\n" +
	            "      \"叶类蔬菜\": [\n" +
	            "        \"芹菜\",\n" +
	            "        \"白菜\",\n" +
	            "        \"韭菜\",\n" +
	            "        \"菠菜\",\n" +
	            "        \"西兰花\",\n" +
	            "        \"圆白菜\",\n" +
	            "        \"花椰菜\",\n" +
	            "        \"莴苣\",\n" +
	            "        \"青菜\",\n" +
	            "        \"娃娃菜\",\n" +
	            "        \"生菜\",\n" +
	            "        \"甘蓝\",\n" +
	            "        \"蒜薹\",\n" +
	            "        \"紫甘蓝\",\n" +
	            "        \"空心菜\",\n" +
	            "        \"油菜\",\n" +
	            "        \"荠菜\",\n" +
	            "        \"香椿\",\n" +
	            "        \"茼蒿\",\n" +
	            "        \"菜心\",\n" +
	            "        \"芥兰\",\n" +
	            "        \"黄花菜\",\n" +
	            "        \"韭黄\",\n" +
	            "        \"苋菜\",\n" +
	            "        \"紫苏\",\n" +
	            "        \"芥菜\",\n" +
	            "        \"油麦菜\",\n" +
	            "        \"豌豆苗\",\n" +
	            "        \"苦菊\",\n" +
	            "        \"青蒜\",\n" +
	            "        \"鱼腥草\",\n" +
	            "        \"马兰\",\n" +
	            "        \"蕨菜\",\n" +
	            "        \"西洋菜\",\n" +
	            "        \"水芹\",\n" +
	            "        \"儿菜\",\n" +
	            "        \"豌豆尖\",\n" +
	            "        \"芝麻菜\",\n" +
	            "        \"芦蒿\",\n" +
	            "        \"穿心莲\",\n" +
	            "        \"孢子甘蓝\",\n" +
	            "        \"萝卜苗\",\n" +
	            "        \"红菜苔\",\n" +
	            "        \"牛至\",\n" +
	            "        \"蒿子杆\"\n" +
	            "      ],\n" +
	            "      \"水果\": [\n" +
	            "        \"水果\",\n" +
	            "        \"椰子\",\n" +
	            "        \"草莓\",\n" +
	            "        \"橙\",\n" +
	            "        \"牛油果\",\n" +
	            "        \"木瓜\",\n" +
	            "        \"山楂\",\n" +
	            "        \"蓝莓\",\n" +
	            "        \"西瓜\",\n" +
	            "        \"柚子\",\n" +
	            "        \"火龙果\",\n" +
	            "        \"樱桃\",\n" +
	            "        \"榴莲\",\n" +
	            "        \"西柚\",\n" +
	            "        \"桃\",\n" +
	            "        \"葡萄\",\n" +
	            "        \"金橘\",\n" +
	            "        \"黄桃\",\n" +
	            "        \"百香果\",\n" +
	            "        \"杨梅\",\n" +
	            "        \"桔子\",\n" +
	            "        \"荔枝\",\n" +
	            "        \"无花果\",\n" +
	            "        \"石榴\",\n" +
	            "        \"杏子\",\n" +
	            "        \"桑葚\",\n" +
	            "        \"哈密瓜\",\n" +
	            "        \"柿子\",\n" +
	            "        \"李子\",\n" +
	            "        \"青梅\",\n" +
	            "        \"枇杷\",\n" +
	            "        \"香瓜\",\n" +
	            "        \"甘蔗\",\n" +
	            "        \"覆盆子\",\n" +
	            "        \"杨桃\"\n" +
	            "      ],\n" +
	            "      \"果实类蔬菜\": [\n" +
	            "        \"彩椒\",\n" +
	            "        \"番茄\",\n" +
	            "        \"南瓜\",\n" +
	            "        \"玉米\",\n" +
	            "        \"红椒\",\n" +
	            "        \"茄子\",\n" +
	            "        \"黄瓜\",\n" +
	            "        \"玉米粒\",\n" +
	            "        \"豇豆\",\n" +
	            "        \"青椒\",\n" +
	            "        \"苦瓜\",\n" +
	            "        \"冬瓜\",\n" +
	            "        \"丝瓜\",\n" +
	            "        \"秋葵\",\n" +
	            "        \"西葫芦\",\n" +
	            "        \"毛豆\",\n" +
	            "        \"豌豆\",\n" +
	            "        \"四季豆\",\n" +
	            "        \"荷兰豆\",\n" +
	            "        \"蚕豆\",\n" +
	            "        \"圣女果\",\n" +
	            "        \"扁豆\",\n" +
	            "        \"刀豆\",\n" +
	            "        \"瓠瓜\",\n" +
	            "        \"玉米笋\"\n" +
	            "      ],\n" +
	            "      \"花朵植物类\": [\n" +
	            "        \"桂花\",\n" +
	            "        \"玫瑰\",\n" +
	            "        \"薄荷\",\n" +
	            "        \"菊花\",\n" +
	            "        \"洛神花\",\n" +
	            "        \"茉莉花\",\n" +
	            "        \"槐花\",\n" +
	            "        \"金银花\"\n" +
	            "      ],\n" +
	            "      \"根茎蔬菜\": [\n" +
	            "        \"土豆\",\n" +
	            "        \"萝卜\",\n" +
	            "        \"紫薯\",\n" +
	            "        \"红薯\",\n" +
	            "        \"胡萝卜\",\n" +
	            "        \"山药\",\n" +
	            "        \"藕\",\n" +
	            "        \"芋头\",\n" +
	            "        \"笋\",\n" +
	            "        \"茭白\",\n" +
	            "        \"马蹄\",\n" +
	            "        \"牛蒡\",\n" +
	            "        \"菱角\"\n" +
	            "      ],\n" +
	            "      \"菌菇类\": [\n" +
	            "        \"香菇\",\n" +
	            "        \"杏鲍菇\",\n" +
	            "        \"银耳\",\n" +
	            "        \"木耳\",\n" +
	            "        \"金针菇\",\n" +
	            "        \"蘑菇\",\n" +
	            "        \"茶树菇\",\n" +
	            "        \"平菇\",\n" +
	            "        \"松茸\",\n" +
	            "        \"鸡腿菇\",\n" +
	            "        \"草菇\",\n" +
	            "        \"竹荪\",\n" +
	            "        \"蟹味菇\",\n" +
	            "        \"花菇\",\n" +
	            "        \"猴头菇\",\n" +
	            "        \"牛肝菌\",\n" +
	            "        \"灵芝\",\n" +
	            "        \"榛蘑\",\n" +
	            "        \"白玉菇\",\n" +
	            "        \"姬松茸\",\n" +
	            "        \"滑子菇\",\n" +
	            "        \"发菜\",\n" +
	            "        \"白灵菇\",\n" +
	            "        \"袖珍菇\",\n" +
	            "        \"双孢菇\"\n" +
	            "      ]\n" +
	            "    },\n" +
	            "    \"烘焙甜品饮料\": {\n" +
	            "      \"甜品\": [\n" +
	            "        \"布丁\",\n" +
	            "        \"冷饮\",\n" +
	            "        \"果酱\",\n" +
	            "        \"糖水\",\n" +
	            "        \"冰淇淋\",\n" +
	            "        \"果冻\",\n" +
	            "        \"甜品\"\n" +
	            "      ],\n" +
	            "      \"烘焙\": [\n" +
	            "        \"蛋糕\",\n" +
	            "        \"面包\",\n" +
	            "        \"饼干\",\n" +
	            "        \"吐司\",\n" +
	            "        \"乳酪蛋糕\",\n" +
	            "        \"曲奇\",\n" +
	            "        \"烘焙\",\n" +
	            "        \"披萨\",\n" +
	            "        \"派\",\n" +
	            "        \"蛋挞\",\n" +
	            "        \"司康\",\n" +
	            "        \"塔\",\n" +
	            "        \"泡芙\",\n" +
	            "        \"奶油霜\"\n" +
	            "      ],\n" +
	            "      \"饮品\": [\n" +
	            "        \"咖啡\",\n" +
	            "        \"豆浆\",\n" +
	            "        \"奶茶\",\n" +
	            "        \"酒\",\n" +
	            "        \"果汁\",\n" +
	            "        \"花草茶\",\n" +
	            "        \"饮品\"\n" +
	            "      ]\n" +
	            "    },\n" +
	            "    \"水产\": {\n" +
	            "      \"螃蟹\": [\n" +
	            "        \"梭子蟹\",\n" +
	            "        \"大闸蟹\",\n" +
	            "        \"蟹肉\",\n" +
	            "        \"螃蟹\",\n" +
	            "        \"蟹黄\"\n" +
	            "      ],\n" +
	            "      \"鱼\": [\n" +
	            "        \"海水鱼\",\n" +
	            "        \"淡水鱼\",\n" +
	            "        \"三文鱼\",\n" +
	            "        \"鲫鱼\",\n" +
	            "        \"金枪鱼\",\n" +
	            "        \"黄鱼\",\n" +
	            "        \"带鱼\",\n" +
	            "        \"鳕鱼\",\n" +
	            "        \"鱼\",\n" +
	            "        \"鱼头\",\n" +
	            "        \"鱼干\",\n" +
	            "        \"鱼籽\",\n" +
	            "        \"鱼肚\"\n" +
	            "      ],\n" +
	            "      \"虾\": [\n" +
	            "        \"虾仁\",\n" +
	            "        \"海米\",\n" +
	            "        \"虾皮\",\n" +
	            "        \"明虾\",\n" +
	            "        \"基围虾\",\n" +
	            "        \"龙虾\",\n" +
	            "        \"小龙虾\",\n" +
	            "        \"河虾\",\n" +
	            "        \"虾\",\n" +
	            "        \"海虾\",\n" +
	            "        \"皮皮虾\",\n" +
	            "        \"北极虾\",\n" +
	            "        \"虾干\",\n" +
	            "        \"青虾\",\n" +
	            "        \"草虾\",\n" +
	            "        \"海白虾\"\n" +
	            "      ],\n" +
	            "      \"贝\": [\n" +
	            "        \"蛤蜊\",\n" +
	            "        \"干贝\",\n" +
	            "        \"鲍鱼\",\n" +
	            "        \"牡蛎\",\n" +
	            "        \"青口\",\n" +
	            "        \"蛏子\",\n" +
	            "        \"鲜贝\",\n" +
	            "        \"北极贝\",\n" +
	            "        \"河蚌\"\n" +
	            "      ]\n" +
	            "    },\n" +
	            "    \"口味特色\": {\n" +
	            "      \"器具\": [\n" +
	            "        \"烤箱\",\n" +
	            "        \"烤箱菜\",\n" +
	            "        \"电饭煲\",\n" +
	            "        \"微波炉\",\n" +
	            "        \"平底锅\",\n" +
	            "        \"塔吉锅\"\n" +
	            "      ],\n" +
	            "      \"异国风味\": [\n" +
	            "        \"日式\",\n" +
	            "        \"韩式\",\n" +
	            "        \"西式\",\n" +
	            "        \"东南亚\"\n" +
	            "      ],\n" +
	            "      \"口味\": [\n" +
	            "        \"辣\",\n" +
	            "        \"咖喱\",\n" +
	            "        \"糖醋\",\n" +
	            "        \"蒜香\",\n" +
	            "        \"酸甜\",\n" +
	            "        \"奶香\",\n" +
	            "        \"孜然\",\n" +
	            "        \"鱼香\",\n" +
	            "        \"五香\",\n" +
	            "        \"清淡\"\n" +
	            "      ],\n" +
	            "      \"烹调方法\": [\n" +
	            "        \"煎\",\n" +
	            "        \"蒸\",\n" +
	            "        \"炖\",\n" +
	            "        \"红烧\",\n" +
	            "        \"炸\",\n" +
	            "        \"卤\",\n" +
	            "        \"干锅\",\n" +
	            "        \"火锅\",\n" +
	            "        \"免烤\"\n" +
	            "      ]\n" +
	            "    },\n" +
	            "    \"肉类\": {\n" +
	            "      \"牛蛙\": [\n" +
	            "        \"牛蛙\"\n" +
	            "      ],\n" +
	            "      \"鸡\": [\n" +
	            "        \"鸡翅\",\n" +
	            "        \"鸡胸\",\n" +
	            "        \"鸡腿\",\n" +
	            "        \"鸡翅中\",\n" +
	            "        \"鸡腿肉\",\n" +
	            "        \"鸡爪\",\n" +
	            "        \"鸡\",\n" +
	            "        \"鸡肉\",\n" +
	            "        \"乌鸡\",\n" +
	            "        \"鸡胗\",\n" +
	            "        \"土鸡\",\n" +
	            "        \"仔鸡\",\n" +
	            "        \"三黄鸡\",\n" +
	            "        \"鸡肝\",\n" +
	            "        \"老母鸡\",\n" +
	            "        \"鸡心\",\n" +
	            "        \"柴鸡\"\n" +
	            "      ],\n" +
	            "      \"鸽子\": [\n" +
	            "        \"鸽子\"\n" +
	            "      ],\n" +
	            "      \"猪\": [\n" +
	            "        \"猪肉\",\n" +
	            "        \"排骨\",\n" +
	            "        \"猪肉末\",\n" +
	            "        \"五花肉\",\n" +
	            "        \"猪蹄\",\n" +
	            "        \"肉丝\",\n" +
	            "        \"肋排\",\n" +
	            "        \"瘦肉\",\n" +
	            "        \"猪\",\n" +
	            "        \"里脊\",\n" +
	            "        \"猪肝\",\n" +
	            "        \"猪排\",\n" +
	            "        \"猪肚\",\n" +
	            "        \"猪皮\",\n" +
	            "        \"猪骨\",\n" +
	            "        \"肥肠\",\n" +
	            "        \"猪油\",\n" +
	            "        \"猪腰\",\n" +
	            "        \"猪耳朵\",\n" +
	            "        \"猪心\",\n" +
	            "        \"猪血\",\n" +
	            "        \"猪肺\"\n" +
	            "      ],\n" +
	            "      \"鸭\": [\n" +
	            "        \"鸭\",\n" +
	            "        \"鸭肉\",\n" +
	            "        \"老鸭\",\n" +
	            "        \"鸭胗\",\n" +
	            "        \"鸭血\",\n" +
	            "        \"鸭掌\",\n" +
	            "        \"鸭翅\",\n" +
	            "        \"鸭舌\",\n" +
	            "        \"鸭肠\",\n" +
	            "        \"鸭脖\",\n" +
	            "        \"鸭肝\"\n" +
	            "      ],\n" +
	            "      \"羊\": [\n" +
	            "        \"羊\",\n" +
	            "        \"羊排\",\n" +
	            "        \"羊腿\",\n" +
	            "        \"羊肉片\",\n" +
	            "        \"羊蝎子\"\n" +
	            "      ],\n" +
	            "      \"牛\": [\n" +
	            "        \"牛肉\",\n" +
	            "        \"牛腩\",\n" +
	            "        \"牛排\",\n" +
	            "        \"肥牛\",\n" +
	            "        \"牛里脊\",\n" +
	            "        \"牛腱\",\n" +
	            "        \"牛\",\n" +
	            "        \"牛尾\",\n" +
	            "        \"牛肉末\",\n" +
	            "        \"牛筋\",\n" +
	            "        \"牛百叶\",\n" +
	            "        \"牛骨\"\n" +
	            "      ]\n" +
	            "    },\n" +
	            "    \"米面干果腌咸\": {\n" +
	            "      \"干果类\": [\n" +
	            "        \"芝麻\",\n" +
	            "        \"花生\",\n" +
	            "        \"杏仁\",\n" +
	            "        \"红豆\",\n" +
	            "        \"白芝麻\",\n" +
	            "        \"黑芝麻\",\n" +
	            "        \"枣\",\n" +
	            "        \"核桃\",\n" +
	            "        \"板栗\",\n" +
	            "        \"蔓越莓\",\n" +
	            "        \"绿豆\",\n" +
	            "        \"黄豆\",\n" +
	            "        \"薏米\",\n" +
	            "        \"葡萄干\",\n" +
	            "        \"莲子\",\n" +
	            "        \"橄榄\",\n" +
	            "        \"黑豆\",\n" +
	            "        \"蜜豆\",\n" +
	            "        \"芸豆\",\n" +
	            "        \"腰果\",\n" +
	            "        \"桃胶\",\n" +
	            "        \"乌梅\",\n" +
	            "        \"松仁\",\n" +
	            "        \"银杏果\",\n" +
	            "        \"话梅\",\n" +
	            "        \"榛子\",\n" +
	            "        \"鹰嘴豆\",\n" +
	            "        \"罗汉果\",\n" +
	            "        \"芡实\",\n" +
	            "        \"开心果\",\n" +
	            "        \"干山楂\",\n" +
	            "        \"南瓜子\",\n" +
	            "        \"瓜子仁\",\n" +
	            "        \"眉豆\",\n" +
	            "        \"红腰豆\"\n" +
	            "      ],\n" +
	            "      \"腌咸制品\": [\n" +
	            "        \"火腿\",\n" +
	            "        \"香肠\",\n" +
	            "        \"培根\",\n" +
	            "        \"火腿肠\",\n" +
	            "        \"腊肉\",\n" +
	            "        \"酸菜\",\n" +
	            "        \"泡菜\",\n" +
	            "        \"肉松\",\n" +
	            "        \"梅干菜\",\n" +
	            "        \"雪里蕻\",\n" +
	            "        \"榨菜\",\n" +
	            "        \"辣白菜\",\n" +
	            "        \"笋干\",\n" +
	            "        \"鱼丸\",\n" +
	            "        \"橄榄菜\",\n" +
	            "        \"咸菜\",\n" +
	            "        \"萝卜干\",\n" +
	            "        \"咸肉\",\n" +
	            "        \"酸豇豆\",\n" +
	            "        \"肉丸\",\n" +
	            "        \"午餐肉\",\n" +
	            "        \"蟹棒\",\n" +
	            "        \"叉烧肉\",\n" +
	            "        \"芽菜\",\n" +
	            "        \"纳豆\",\n" +
	            "        \"大头菜\",\n" +
	            "        \"酸笋\",\n" +
	            "        \"木鱼花\",\n" +
	            "        \"冬菜\",\n" +
	            "        \"玉兰片\",\n" +
	            "        \"泡萝卜\",\n" +
	            "        \"咸黄瓜\"\n" +
	            "      ],\n" +
	            "      \"米面类\": [\n" +
	            "        \"面粉\",\n" +
	            "        \"面条\",\n" +
	            "        \"大米\",\n" +
	            "        \"高筋面粉\",\n" +
	            "        \"意大利面\",\n" +
	            "        \"糯米\",\n" +
	            "        \"低筋面粉\",\n" +
	            "        \"年糕\",\n" +
	            "        \"西米\",\n" +
	            "        \"糯米粉\",\n" +
	            "        \"粉丝\",\n" +
	            "        \"燕麦\",\n" +
	            "        \"米粉\",\n" +
	            "        \"酒酿\",\n" +
	            "        \"燕麦片\",\n" +
	            "        \"乌冬面\",\n" +
	            "        \"小米\",\n" +
	            "        \"玉米面\",\n" +
	            "        \"全麦粉\",\n" +
	            "        \"黑米\",\n" +
	            "        \"魔芋\",\n" +
	            "        \"方便面\",\n" +
	            "        \"粉条\",\n" +
	            "        \"米线\",\n" +
	            "        \"荞麦面\",\n" +
	            "        \"油条\",\n" +
	            "        \"黄豆面\",\n" +
	            "        \"消化饼干\",\n" +
	            "        \"面筋\",\n" +
	            "        \"粘米粉\",\n" +
	            "        \"凉粉\",\n" +
	            "        \"糙米\",\n" +
	            "        \"河粉\",\n" +
	            "        \"紫米\",\n" +
	            "        \"烤麸\",\n" +
	            "        \"小麦胚芽\",\n" +
	            "        \"澄面\",\n" +
	            "        \"粉皮\",\n" +
	            "        \"蕨根粉\",\n" +
	            "        \"藕粉\",\n" +
	            "        \"红曲米\",\n" +
	            "        \"莜面\",\n" +
	            "        \"黄米\",\n" +
	            "        \"荞麦\",\n" +
	            "        \"大麦\",\n" +
	            "        \"高粱米\"\n" +
	            "      ]\n" +
	            "    },\n" +
	            "    \"热门专题\": {\n" +
	            "      \"功效\": [\n" +
	            "        \"减肥\",\n" +
	            "        \"美容\",\n" +
	            "        \"润肺抗燥\",\n" +
	            "        \"补血\",\n" +
	            "        \"清热祛火\"\n" +
	            "      ],\n" +
	            "      \"人群\": [\n" +
	            "        \"儿童\",\n" +
	            "        \"婴幼儿\",\n" +
	            "        \"老人\",\n" +
	            "        \"孕产妇\"\n" +
	            "      ],\n" +
	            "      \"菜式\": [\n" +
	            "        \"家常菜\",\n" +
	            "        \"快手菜\",\n" +
	            "        \"下饭菜\",\n" +
	            "        \"素菜\",\n" +
	            "        \"大鱼大肉\",\n" +
	            "        \"下酒菜\",\n" +
	            "        \"小清新\",\n" +
	            "        \"创意菜\"\n" +
	            "      ],\n" +
	            "      \"特殊场合\": [\n" +
	            "        \"早餐\",\n" +
	            "        \"下午茶\",\n" +
	            "        \"便当\",\n" +
	            "        \"圣诞节\",\n" +
	            "        \"年夜饭\",\n" +
	            "        \"深夜食堂\",\n" +
	            "        \"情人节\",\n" +
	            "        \"宵夜\"\n" +
	            "      ],\n" +
	            "      \"特色食品\": [\n" +
	            "        \"小吃\",\n" +
	            "        \"酱\",\n" +
	            "        \"沙拉\",\n" +
	            "        \"凉菜\",\n" +
	            "        \"零食\",\n" +
	            "        \"三明治\",\n" +
	            "        \"月饼\",\n" +
	            "        \"蒸蛋\",\n" +
	            "        \"寿司\",\n" +
	            "        \"粽子\"\n" +
	            "      ]\n" +
	            "    }\n" +
	            "  }\n" +
	            "}";

		
		
		JSONObject json = JSONObject.parseObject(cate);
		JSONObject showapi_res_body = json.getJSONObject("showapi_res_body");
		showapi_res_body.keySet().forEach(key -> {
			JSONObject json2 = showapi_res_body.getJSONObject(key);
			System.out.println(key);
			
			CmsMenuCategoryEntity cate1 = save(key, -1,0);
			
			if (json2 != null) {
				json2.keySet().forEach(key2 -> {
					System.out.println("2----------"+key2);
					JSONArray json3 = json2.getJSONArray(key2);
					if (json3 != null) {
						CmsMenuCategoryEntity cate2 = save(key2, cate1.getId(),0);
						json3.forEach(key3 -> {
							System.out.println("3------------"+key3);
							save(key3.toString(), cate2.getId(),1);
						});
					} else {
						save(key2, cate1.getId(),1);
					}
				});
			}
		});
		
	}
	
	public CmsMenuCategoryEntity save(String name, Integer parntId, Integer hasNext) {
		CmsMenuCategoryEntity category = new CmsMenuCategoryEntity();
		category.setCategoryName(name);
		category.setIsDeleted(0);
		category.setParentId(parntId);
		category.setCreateTime(new Date());
		category.setUpdateTime(new Date());
		category.setHasNext(hasNext);
		cmsMenuCategoryService.save(category);
		return category;
	}
	
	@Test
	public void selectByParentIdTest() {
		cmsMenuCategoryService.selectByParentId(-1);
	}

}
