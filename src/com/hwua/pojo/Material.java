package com.hwua.pojo;

public class Material {
	
    private long id;   //id

    private String numbers;   //编号

    private String typeid;      //类型

    private String name;      //名字

    private String type;      //型号

    private String standstandard;   //规格

    private double price;     //单价 

    private String factory;   //厂家

    private String leavefactorydate;   //出厂日期

    private String buydate;    //购买日期

    private String indate;     //入库日期

    private String place;      //存放地点

    private String usesituation;   //使用情况

    private long count;     //使用数量

    private long surplus;    //库存数量
    
    private String image;    //二维码存放地址
    
    private String remarks;  //是否缺标签
    
    private int max;
    
    private int min;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStandstandard() {
		return standstandard;
	}

	public void setStandstandard(String standstandard) {
		this.standstandard = standstandard;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getLeavefactorydate() {
		return leavefactorydate;
	}

	public void setLeavefactorydate(String leavefactorydate) {
		this.leavefactorydate = leavefactorydate;
	}

	public String getBuydate() {
		return buydate;
	}

	public void setBuydate(String buydate) {
		this.buydate = buydate;
	}

	public String getIndate() {
		return indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getUsesituation() {
		return usesituation;
	}

	public void setUsesituation(String usesituation) {
		this.usesituation = usesituation;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getSurplus() {
		return surplus;
	}

	public void setSurplus(long surplus) {
		this.surplus = surplus;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public Material() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Material(int max, int min) {
		super();
		this.max = max;
		this.min = min;
	}

	public Material(long id, String numbers, String typeid, String name, String type, String standstandard, double price,
			String factory, String leavefactorydate, String buydate, String indate, String place, String usesituation,
			long count, long surplus, String image, String remarks, int max, int min) {
		super();
		this.id = id;
		this.numbers = numbers;
		this.typeid = typeid;
		this.name = name;
		this.type = type;
		this.standstandard = standstandard;
		this.price = price;
		this.factory = factory;
		this.leavefactorydate = leavefactorydate;
		this.buydate = buydate;
		this.indate = indate;
		this.place = place;
		this.usesituation = usesituation;
		this.count = count;
		this.surplus = surplus;
		this.image = image;
		this.remarks = remarks;
		this.max = max;
		this.min = min;
	}

	public Material(long id, String numbers, String typeid, String name, String type, String standstandard, double price,
			String factory, String leavefactorydate, String buydate, String indate, String place, String usesituation,
			long count, long surplus, String image, String remarks) {
		super();
		this.id = id;
		this.numbers = numbers;
		this.typeid = typeid;
		this.name = name;
		this.type = type;
		this.standstandard = standstandard;
		this.price = price;
		this.factory = factory;
		this.leavefactorydate = leavefactorydate;
		this.buydate = buydate;
		this.indate = indate;
		this.place = place;
		this.usesituation = usesituation;
		this.count = count;
		this.surplus = surplus;
		this.image = image;
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Material [id=" + id + ", numbers=" + numbers + ", typeid=" + typeid + ", name=" + name + ", type="
				+ type + ", standstandard=" + standstandard + ", price=" + price + ", factory=" + factory
				+ ", leavefactorydate=" + leavefactorydate + ", buydate=" + buydate + ", indate=" + indate + ", place="
				+ place + ", usesituation=" + usesituation + ", count=" + count + ", surplus=" + surplus + ", image="
				+ image + ", remarks=" + remarks + "]";
	}

	public Material(String numbers, String typeid, String name, String type, String standstandard, double price,
			String factory, String leavefactorydate, String buydate, String indate, String place, String usesituation,
			long count, long surplus, String image, String remarks) {
		super();
		this.numbers = numbers;
		this.typeid = typeid;
		this.name = name;
		this.type = type;
		this.standstandard = standstandard;
		this.price = price;
		this.factory = factory;
		this.leavefactorydate = leavefactorydate;
		this.buydate = buydate;
		this.indate = indate;
		this.place = place;
		this.usesituation = usesituation;
		this.count = count;
		this.surplus = surplus;
		this.image = image;
		this.remarks = remarks;
	}
    

    }