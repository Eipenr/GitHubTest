###整合步骤
1. 导入依赖包,在导入 spring 和 mybatis 需要的依赖包之后 再导入 mybatis-spring 和 spring-jdbc
2. 正常写 mybatis 的接口和mapper.xml 文件
3. 整合 
    1. 需要整合数据库,通过创建 druid 的连接池对象,将数据库信息设置进去
    2. 创建 sqlsessionfactory, 通过 mybatis 给我们提供的 sqlsessionfactorybean 来创建,创建的时候我们只需要指定数据库,把上面创建的连接池配置过来了
    3. 我们还要注解 mapper 接口和 xml 文件, 通过配置MapperScannerConfigurer这个对象来进行设置 主要 basepackage 扫描的位置必须精确到 mapper 接口所在的包,不能范围过大,否则可能会出现异常
    4. 正常通过 spring 获取 mapper 对象执行测试, mapper 全部由 spring 创建