package Enum;


public enum PatternEnum {
															//xxx
	header			("${header}",2,"an") ,					//1.a
	dealDate		("${dealDate}",8,"an") ,				//2.b
	dateRecordCount	("${dateRecordCount}",8,"n"),			//3.c
	acqMemId		("${acqMemId}",8,"an"),					//4.d
	validateNum		("${validateNum}",8,"an"),				//5.e			
	reserved		("${reserved}",8,"an"),					//6.f
	carriageReturn	("${carriageReturn}",1,"an"),			//7.
	lineFeed		("${lineFeed}",1,"an"),					//8.
	
															//Data Record
	pCode			("${pCode}",4,"an"),					//1.aaa
	merchId			("${merchId}",16,"an");					//2.
	
	
	private String pattern;
	private Integer length;
	private String format;
	
	private PatternEnum(String pattern,Integer length,String format) {
		this.pattern = pattern;
		this.length = length;
		this.format = format;
	}

	public static void main(String[]args){
		System.out.println();
		for(PatternEnum pattern : PatternEnum.values()){
			System.out.println(pattern);
		}
	}
}
