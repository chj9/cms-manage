package com.chj9.cms.menu;

import java.util.ArrayList;
import java.util.List;

import com.chj9.cms.api.entity.CmsMenuCategoryEntity;
import com.chj9.cms.api.entity.CmsMenuEntity;
import com.chj9.cms.api.entity.CmsMenuMaterialEntity;
import com.chj9.cms.api.entity.CmsMenuStepEntity;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chj9.cms.service.CmsMenuCategoryService;
import com.chj9.cms.service.CmsMenuService;
import com.chj9.cms.service.FileService;
import com.chj9.cms.api.vo.CmsMenuParam;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MenuApplication.class)
public class DemoTest {

	@Autowired
	private CmsMenuService cmsMenuService;
	@Autowired
	private CmsMenuCategoryService cmsMenuCategoryService;
	@Autowired
	private FileService fileService;

	@Test
	public void selectUserTest() {
		// CmsMenuParam param = new CmsMenuParam();

		// cmsMenuService.createMenu(param);
		String str[] = new String[] { "牛蛙", "鸽子", "鸡翅", "鸡胸", "鸡腿", "鸡翅中", "鸡腿肉", "鸡爪", "鸡", "鸡肉", "乌鸡", "鸡胗", "土鸡",
				"仔鸡", "三黄鸡", "鸡肝", "老母鸡", "鸡心", "柴鸡", "猪肉", "排骨", "猪肉末", "五花肉", "猪蹄", "肉丝", "肋排", "瘦肉", "猪", "里脊", "猪肝",
				"猪排", "猪肚", "猪皮", "猪骨", "肥肠", "猪油", "猪腰", "猪耳朵", "猪心", "猪血", "猪肺", "羊", "羊排", "羊腿", "羊肉片", "羊蝎子", "牛肉",
				"牛腩", "牛排", "肥牛", "牛里脊", "牛腱", "牛", "牛尾", "牛肉末", "牛筋", "牛百叶", "牛骨", "鸭", "鸭肉", "老鸭", "鸭胗", "鸭血", "鸭掌",
				"鸭翅", "鸭舌", "鸭肠", "鸭脖", "鸭肝", "儿童", "婴幼儿", "老人", "孕产妇", "减肥", "美容", "润肺抗燥", "补血", "清热祛火", "家常菜", "快手菜",
				"下饭菜", "素菜", "大鱼大肉", "下酒菜", "小清新", "创意菜", "早餐", "下午茶", "便当", "圣诞节", "年夜饭", "深夜食堂", "情人节", "宵夜", "小吃",
				"酱", "沙拉", "凉菜", "零食", "三明治", "月饼", "蒸蛋", "寿司", "粽子", "煎", "蒸", "炖", "红烧", "炸", "卤", "干锅", "火锅", "免烤",
				"烤箱", "烤箱菜", "电饭煲", "微波炉", "平底锅", "塔吉锅", "日式", "韩式", "西式", "东南亚", "辣", "咖喱", "糖醋", "蒜香", "酸甜", "奶香",
				"孜然", "鱼香", "五香", "清淡", "火腿", "香肠", "培根", "火腿肠", "腊肉", "酸菜", "泡菜", "肉松", "梅干菜", "雪里蕻", "榨菜", "辣白菜",
				"笋干", "鱼丸", "橄榄菜", "咸菜", "萝卜干", "咸肉", "酸豇豆", "肉丸", "午餐肉", "蟹棒", "叉烧肉", "芽菜", "纳豆", "大头菜", "酸笋", "木鱼花",
				"冬菜", "玉兰片", "泡萝卜", "咸黄瓜", "面粉", "面条", "大米", "高筋面粉", "意大利面", "糯米", "低筋面粉", "年糕", "西米", "糯米粉", "粉丝",
				"燕麦", "米粉", "酒酿", "燕麦片", "乌冬面", "小米", "玉米面", "全麦粉", "黑米", "魔芋", "方便面", "粉条", "米线", "荞麦面", "油条", "黄豆面",
				"消化饼干", "面筋", "粘米粉", "凉粉", "糙米", "河粉", "紫米", "烤麸", "小麦胚芽", "澄面", "粉皮", "蕨根粉", "藕粉", "红曲米", "莜面", "黄米",
				"荞麦", "大麦", "高粱米", "芝麻", "花生", "杏仁", "红豆", "白芝麻", "黑芝麻", "枣", "核桃", "板栗", "蔓越莓", "绿豆", "黄豆", "薏米",
				"葡萄干", "莲子", "橄榄", "黑豆", "蜜豆", "芸豆", "腰果", "桃胶", "乌梅", "松仁", "银杏果", "话梅", "榛子", "鹰嘴豆", "罗汉果", "芡实",
				"开心果", "干山楂", "南瓜子", "瓜子仁", "眉豆", "红腰豆", "面", "炒面", "汤面", "凉面", "焖面", "汤羹", "饼", "饭", "焖", "饭团", "盖浇饭",
				"煲仔饭", "焗饭", "烩饭", "咖啡", "豆浆", "奶茶", "酒", "果汁", "花草茶", "饮品", "布丁", "冷饮", "果酱", "糖水", "冰淇淋", "果冻", "甜品",
				"蛋糕", "面包", "饼干", "吐司", "乳酪蛋糕", "曲奇", "烘焙", "披萨", "派", "蛋挞", "司康", "塔", "泡芙", "奶油霜", "鸡蛋", "咸蛋", "皮蛋",
				"咸蛋黄", "鹌鹑蛋", "土鸡蛋", "鸭蛋", "豆腐", "香干", "豆渣", "千张", "北豆腐", "腐竹", "素鸡", "油豆皮", "牛奶", "黄油", "巧克力", "奶酪",
				"酸奶", "无盐黄油", "黑巧克力", "淡奶油", "奶油奶酪", "炼乳", "奶粉", "马苏里拉...", "白巧克力", "酸奶油", "酥油", "水果", "椰子", "草莓", "橙",
				"牛油果", "木瓜", "山楂", "蓝莓", "西瓜", "柚子", "火龙果", "樱桃", "榴莲", "西柚", "桃", "葡萄", "金橘", "黄桃", "百香果", "杨梅", "桔子",
				"荔枝", "无花果", "石榴", "杏子", "桑葚", "哈密瓜", "柿子", "李子", "青梅", "枇杷", "香瓜", "甘蔗", "覆盆子", "杨桃", "土豆", "萝卜", "紫薯",
				"红薯", "胡萝卜", "山药", "藕", "芋头", "笋", "茭白", "马蹄", "牛蒡", "菱角", "芹菜", "白菜", "韭菜", "菠菜", "西兰花", "圆白菜", "花椰菜",
				"莴苣", "青菜", "娃娃菜", "生菜", "甘蓝", "蒜薹", "紫甘蓝", "空心菜", "油菜", "荠菜", "香椿", "茼蒿", "菜心", "芥兰", "黄花菜", "韭黄",
				"苋菜", "紫苏", "芥菜", "油麦菜", "豌豆苗", "苦菊", "青蒜", "鱼腥草", "马兰", "蕨菜", "西洋菜", "水芹", "儿菜", "豌豆尖", "芝麻菜", "芦蒿",
				"穿心莲", "孢子甘蓝", "萝卜苗", "红菜苔", "牛至", "蒿子杆", "桂花", "玫瑰", "薄荷", "菊花", "洛神花", "茉莉花", "槐花", "金银花", "彩椒", "番茄",
				"南瓜", "玉米", "红椒", "茄子", "黄瓜", "玉米粒", "豇豆", "青椒", "苦瓜", "冬瓜", "丝瓜", "秋葵", "西葫芦", "毛豆", "豌豆", "四季豆",
				"荷兰豆", "蚕豆", "圣女果", "扁豆", "刀豆", "瓠瓜", "玉米笋", "香菇", "杏鲍菇", "银耳", "木耳", "金针菇", "蘑菇", "茶树菇", "平菇", "松茸",
				"鸡腿菇", "草菇", "竹荪", "蟹味菇", "花菇", "猴头菇", "牛肝菌", "灵芝", "榛蘑", "白玉菇", "姬松茸", "滑子菇", "发菜", "白灵菇", "袖珍菇",
				"双孢菇", "梭子蟹", "大闸蟹", "蟹肉", "螃蟹", "蟹黄", "海水鱼", "淡水鱼", "三文鱼", "鲫鱼", "金枪鱼", "黄鱼", "带鱼", "鳕鱼", "鱼", "鱼头",
				"鱼干", "鱼籽", "鱼肚", "蛤蜊", "干贝", "鲍鱼", "牡蛎", "青口", "蛏子", "鲜贝", "北极贝", "河蚌", "虾仁", "海米", "虾皮", "明虾", "基围虾",
				"龙虾", "小龙虾", "河虾", "虾", "海虾", "皮皮虾", "北极虾", "虾干", "青虾", "草虾", "海白虾" };
		for (int i = 0; i < str.length; i++) {
			data(str[i], 3);
		}

	}

	public void data(String kw, int pageNo) {
		String host = "http://caipu.market.alicloudapi.com";
		String path = "/showapi_cpQuery";
		String appcode = "4c43eef9dc61485583e7a92785f04ffe";

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Authorization", "APPCODE " + appcode);

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);

		String url = host + path + "?type=" + kw + "&maxResults=50&page=" + pageNo;
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
		String sttr = response.getBody();
		JSONObject json = (JSONObject) JSONObject.parse(sttr);
		JSONObject result = (JSONObject) json.get("showapi_res_body");
		JSONArray array = (JSONArray) result.get("datas");

		for (Object object : array) {
			try {

				JSONObject obj = (JSONObject) object;
				List<CmsMenuEntity> selectByRefId = cmsMenuService.selectByRefId(obj.getString("id"));
				if (selectByRefId == null || selectByRefId.size() > 0) {
					continue;
				}
				CmsMenuParam param = new CmsMenuParam();

				param.setMenuName(obj.getString("cpName"));
				param.setMenuDesc(obj.getString("des"));
				param.setMenuImg(getImg(obj.getString("largeImg"), "主图"));
				param.setMenuTip(obj.getString("tip"));
				param.setRefId(obj.getString("id"));
				// 保存分类
				List<CmsMenuCategoryEntity> cateList = cmsMenuCategoryService.selectLastCategory(obj.getString("type_v3"));

				if (cateList != null && cateList.size() > 0) {
					param.setCategoryId(cateList.get(0).getId());
					param.setCategoryName(cateList.get(0).getCategoryName());
				}
				JSONArray steps = (JSONArray) obj.get("steps");
				// 保存步骤
				List<CmsMenuStepEntity> stepList = saveStep(steps);
				param.setStepList(stepList);
				// 保存材料
				JSONArray yls = (JSONArray) obj.get("yl");
				List<CmsMenuMaterialEntity> materialList = saveMaterial(yls);
				param.setMaterialList(materialList);
				cmsMenuService.createMenu(param);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public CmsMenuCategoryEntity saveCate(String type1, String type2, String type3) {
		CmsMenuCategoryEntity cate = new CmsMenuCategoryEntity();
		// 三级 二级都不为空
		if (StringUtils.isNotEmpty(type3) && StringUtils.isNotEmpty(type2) && StringUtils.isNotEmpty(type1)) {
			// 先保存一级
			CmsMenuCategoryEntity cate1 = cmsMenuCategoryService.save(type1, -1L);
			if (cate1 != null) {
				CmsMenuCategoryEntity cate2 = cmsMenuCategoryService.save(type2, cate1.getId());
				if (cate2 != null) {
					return cmsMenuCategoryService.save(type3, cate2.getId());
				}
			}

		}
		if (StringUtils.isEmpty(type3) && StringUtils.isNotEmpty(type2) && StringUtils.isNotEmpty(type1)) {
			// 先保存一级
			CmsMenuCategoryEntity cate1 = cmsMenuCategoryService.save(type1, -1L);
			if (cate1 != null) {
				return cmsMenuCategoryService.save(type2, cate1.getId());
			}
		}
		if (StringUtils.isEmpty(type3) && StringUtils.isEmpty(type2) && StringUtils.isNotEmpty(type1)) {
			// 先保存一级
			return cmsMenuCategoryService.save(type1, -1L);
		}
		return cate;
	}

	public List<CmsMenuStepEntity> saveStep(JSONArray steps) {
		List<CmsMenuStepEntity> stepList = new ArrayList<>();
		for (Object obj : steps) {
			JSONObject step = (JSONObject) obj;
			String imgUrl = step.getString("imgUrl");
			Integer orderNum = step.getInteger("orderNum");
			String content = step.getString("content");

			CmsMenuStepEntity menuStep = new CmsMenuStepEntity();
			menuStep.setStepDesc(content);
			menuStep.setStepIndex(orderNum);
			menuStep.setStepImg(getImg(imgUrl, "step" + orderNum));
			stepList.add(menuStep);

		}
		return stepList;
	}

	public List<CmsMenuMaterialEntity> saveMaterial(JSONArray yls) {
		List<CmsMenuMaterialEntity> materialList = new ArrayList<>();
		for (Object obj : yls) {
			JSONObject step = (JSONObject) obj;
			String ylUnit = step.getString("ylUnit");
			String ylName = step.getString("ylName");

			CmsMenuMaterialEntity material = new CmsMenuMaterialEntity();
			material.setMaterialName(ylName);
			material.setMaterialDesc(ylUnit);
			materialList.add(material);

		}
		return materialList;
	}

	public String getImg(String url, String name) {
		if (StringUtils.isNotEmpty(url)) {
			String fileId = fileService.putFile(url, name + "." + url.substring(url.lastIndexOf(".") + 1));
			return fileId;
		} else {
			return null;
		}

	}

}
