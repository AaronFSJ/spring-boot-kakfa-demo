#spring boot+kafka发送对象的demo

>注册事项
- spring boot默认生产者的key.deserializer和消息者的key.deserializer一般使用String类型，即生产者用StringSerializer，消息者用StringDeserializer，即String序列化，因此配置文件中key-deserialize和value-deserialize可以不指定。但是如果指定了之后，存入的key或者value为空，就会报错
- 如果不是非必须生产者和消息者的value.deserializer可以使用StringDeserializer，直接接收String数据，如果想要接收对象，可以先将对象转成json字符串发送，在消息者端将json串解释成对象。
- 如果不使用StringDeserializer，可重新在配置文件中定义