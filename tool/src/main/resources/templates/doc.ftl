{
	"info": {
		"_postman_id": "${uuid}",
		"name": "${table.tableComment}",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
	},
	"item": [
		{
			"name": "查询分页",
			"protocolProfileBehavior": {
				"disableBodyPruning": true
			},
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "urlencoded",
					"urlencoded": [
						<#list table.columns as column>
							<#if column.unique>
								{
									"key": "${column.javaVarName}Eq",
									"value": "",
									"description": "${column.columnComment}，等于查询",
									"type": "text"
								},
							</#if>
						</#list>
					]
				},
				"url": {
					"raw": "http://localhost:8082/${urlPrefix}/${table.controllerPath}/page",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8082",
					"path": [
						"${urlPrefix}","${table.controllerPath?split("/")?join('","')}","page"
					]
				},
				"description": "${table.tableComment}查询分页数据接口\r\n\r\n权限条件：需要当前用户存在\"perms[${table.pluginName}:page]\"才能使用\r\n\r\n响应信息说明：\r\n\r\n\t\"message\": \"执行后的信息\"\r\n\t\"status\": \"状态码，200 表示服务器执行成功\"\r\n\t\"executeCode\": \"执行代码，200 表示执行成功，否则根据对应的代码去查看错误类型\"\r\n\t\"timestamp\": \"时间戳\"\r\n\t\r\n响应信息 data 说明：\r\n\r\n\t\"number\": 当前页码\r\n\t\"size\": 分页大小\r\n\t\"content\": 内容\r\n\t\"numberOfElements\":当前结果数量\r\n\t\"first\": 是否存在上一页，true 是，否则 false\r\n\t\"last\": 是否存在下一页，true是，否则 false\r\n\r\ncontent 内容说明：\r\n\t    \r\n<#list table.columns as column>    \"${column.javaVarName}\": \"${column.columnComment}<#if !column.nullable>, 不能为空</#if>\"\r\n</#list>"
			},
			"response": []
		},
		{
			"name": "获取数据",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8082/${urlPrefix}/${table.controllerPath}/get?id=",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8082",
					"path": [
						"${urlPrefix}","${table.controllerPath?split("/")?join('","')}","get"
					],
					"query": [
						{
							"key": "id",
							"value": "",
							"description": "主键 id"
						}
					]
				},
				"description": "获取${table.tableComment}信息的接口：\n\n权限条件：需要当前用户存在\"perms[${table.pluginName}:get]\"才能使用\n\n响应信息说明：\n\n\t\"message\": \"执行后的信息\"\n\t\"status\": \"状态码，200 表示服务器执行成功\"\n\t\"executeCode\": \"执行代码，200 表示执行成功，否则根据对应的代码去查看错误类型\"\n\t\"timestamp\": \"时间戳\"\n\t\n具体 data 响应信息为：\r\n\t    \r\n<#list table.columns as column>    \"${column.javaVarName}\": \"${column.columnComment}<#if !column.nullable>, 不能为空</#if>\"\r\n</#list>"
			},
			"response": []
		},
		{
			"name": "保存数据",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "urlencoded",
					"urlencoded": [
						<#list table.columns as column>
						<#if ignoreProperties?seq_contains(column.javaVarName) == false>
						{
							"key": "${column.javaVarName}",
							"value": "",
							"description": "${column.columnComment}<#if !column.nullable>, 不能为空</#if><#if column.size gt 0 && column.javaTypeName == "String">, 长度 ${(column.size / 2)?int}</#if>",
							"type": "text"
						}<#if column_index != table.columns?size - 1>,</#if>
						</#if>
						</#list>
					],
					"options": {
						"urlencoded": {}
					}
				},
				"url": {
					"raw": "http://localhost:8082/${urlPrefix}/${table.controllerPath}/save",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8082",
					"path": [
						"${urlPrefix}","${table.controllerPath?split("/")?join('","')}","save"
					]
				},
				"description": "保存${table.tableComment}接口，用于添加或编辑页面输入完成时，提交保存的接口\n\n权限条件：需要当前用户存在\"perms[${table.pluginName}:save]\"才能使用\n\n响应信息说明：\n\n\t\"message\": \"执行后的信息\"\n\t\"status\": \"状态码，200 表示服务器执行成功\"\n\t\"executeCode\": \"执行代码，200 表示执行成功，否则根据对应的代码去查看错误类型\"\n\t\"timestamp\": \"时间戳\"\n\n响应信息 data 说明：\n\n\tid：当前处理的数据 id"
			},
			"response": []
		},
		{
			"name": "删除数据",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "urlencoded",
					"urlencoded": [
						{
							"key": "ids",
							"value": "",
							"description": "主键 id 集合",
							"type": "text"
						}
					],
					"options": {
						"urlencoded": {}
					}
				},
				"url": {
					"raw": "http://localhost:8082/${urlPrefix}/${table.controllerPath}/delete",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8082",
					"path": [
						"${urlPrefix}","${table.controllerPath?split("/")?join('","')}","delete"
					]
				},
				"description": "删除${table.tableComment}接口，ids 参数为数据 id 集合\n\n前端：\n\n1.在列表页面中可以通过复选框选中多个数据在提交 form 表单到此接口\n\n权限条件：需要当前用户存在\"perms[${table.pluginName}:delete]\"才能使用\n\n响应信息说明：\n\n\t\"message\": \"执行后的信息\"\n\t\"status\": \"状态码，200 表示服务器执行成功\"\n\t\"executeCode\": \"执行代码，200 表示执行成功，否则根据对应的代码去查看错误类型\"\n\t\"timestamp\": \"时间戳\""
			},
			"response": []
		}
		<#list table.columns as column>
			<#if column.unique>
				,{
					"name": "判断${column.javaName}是否唯一",
					"request": {
						"method": "GET",
						"header": [],
						"body": {
							"mode": "urlencoded",
							"urlencoded": [
								{
									"key": "${column.javaVarName}",
									"value": "",
									"description": "${column.columnComment}",
									"type": "text"
								}
							],
							"options": {
								"urlencoded": {}
							}
						},
						"url": {
							"raw": "http://localhost:8082/${urlPrefix}/${table.controllerPath}/is${column.javaName}Unique",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8082",
							"path": [
								"${urlPrefix}","${table.controllerPath?split("/")?join('","')}","is${column.javaName}Unique"
							]
						},
						"description": "根据用户输入的${column.javaName}，判断该${column.javaName}是否唯一\n\n权限条件：需要当前用户认证成功后才能使用\n\n响应信息说明：\n\n\ttrue 唯一，否则 false"
					},
					"response": []
				}
			</#if>
		</#list>
	],
	"protocolProfileBehavior": {}
}