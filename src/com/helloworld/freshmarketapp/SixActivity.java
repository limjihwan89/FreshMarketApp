package com.helloworld.freshmarketapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class SixActivity extends Activity {

	TextView tvCategory;
	Spinner spnCategory1;
	Spinner spnCategory2;
	Button btnPrevious3;
	Button btnNext4;
	
	ArrayAdapter<CharSequence> mainAdapter, subAdapter;
	public static String category1, category2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.registration_four);

		tvCategory = (TextView) this.findViewById(R.id.tv_category);
		spnCategory1 = (Spinner) this.findViewById(R.id.spn_category1);
		spnCategory2 = (Spinner) this.findViewById(R.id.spn_category2);
		btnPrevious3 = (Button) this.findViewById(R.id.btn_previous3);
		btnNext4 = (Button) this.findViewById(R.id.btn_next4);
		
		mainAdapter = ArrayAdapter.createFromResource(SixActivity.this, R.array.categories1,
													android.R.layout.simple_spinner_item);
		mainAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		//칸사이 간격을 넓게해주는 setDropDownViewResource메소드
		subAdapter = ArrayAdapter.createFromResource(SixActivity.this, R.array.categories2,
													android.R.layout.simple_spinner_item);
		subAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		
		spnCategory1.setAdapter(mainAdapter);	
		spnCategory2.setAdapter(subAdapter);
		
		btnPrevious3.setOnClickListener(onClick);
		btnNext4.setOnClickListener(onClick);
		
		spnCategory1.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(parent.getItemAtPosition(position).toString().equals("의류")) {
					category1 = "1";
					subAdapter = ArrayAdapter.createFromResource(SixActivity.this, R.array.clothes,
							android.R.layout.simple_spinner_item);
					subAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
					spnCategory2.setAdapter(subAdapter);
					
					spnCategory2.setOnItemSelectedListener(new OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							if(parent.getItemAtPosition(position).toString().equals("캐쥬얼상의")) {
								category2 = "1";
							} else if(parent.getItemAtPosition(position).toString().equals("캐쥬얼하의")) {
								category2 = "2";
							} else if(parent.getItemAtPosition(position).toString().equals("여성상의")) {
								category2 = "3";
							} else if(parent.getItemAtPosition(position).toString().equals("여성하의")) {
								category2 = "4";
							} else if(parent.getItemAtPosition(position).toString().equals("남성상의")) {
								category2 = "5";
							} else if(parent.getItemAtPosition(position).toString().equals("남성하의")) {
								category2 = "6";
							} else if(parent.getItemAtPosition(position).toString().equals("파티/잠옷/작업복")) {
								category2 = "7";
							}
						}
						@Override
						public void onNothingSelected(AdapterView<?> parent) {
						}					
					});
					
				} else if(parent.getItemAtPosition(position).toString().equals("수입명품")) {
					category1 = "2";
					subAdapter = ArrayAdapter.createFromResource(SixActivity.this, R.array.imports,
							android.R.layout.simple_spinner_item);
					subAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
					spnCategory2.setAdapter(subAdapter);
					
					spnCategory2.setOnItemSelectedListener(new OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							if(parent.getItemAtPosition(position).toString().equals("루이비통")) {
								category2 = "1";
							} else if(parent.getItemAtPosition(position).toString().equals("샤넬")) {
								category2 = "2";
							} else if(parent.getItemAtPosition(position).toString().equals("버버리/마크제이콥스")) {
								category2 = "3";
							} else if(parent.getItemAtPosition(position).toString().equals("불가리/까르띠에")) {
								category2 = "4";
							} else if(parent.getItemAtPosition(position).toString().equals("구찌/프라다")) {
								category2 = "5";
							} else if(parent.getItemAtPosition(position).toString().equals("디올/입생로랑")) {
								category2 = "6";
							} else if(parent.getItemAtPosition(position).toString().equals("에르메스/콜롬보")) {
								category2 = "7";
							} else if(parent.getItemAtPosition(position).toString().equals("발리/에트로/페라가모")) {
								category2 = "8";
							} else if(parent.getItemAtPosition(position).toString().equals("기타 수입명품")) {
								category2 = "9";
							}
							
						}
						@Override
						public void onNothingSelected(AdapterView<?> parent) {
						}						
					});
				} else if(parent.getItemAtPosition(position).toString().equals("패션잡화")) {
					category1 = "3";
					subAdapter = ArrayAdapter.createFromResource(SixActivity.this, R.array.fashion,
							android.R.layout.simple_spinner_item);
					subAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
					spnCategory2.setAdapter(subAdapter);
					
					spnCategory2.setOnItemSelectedListener(new OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							if(parent.getItemAtPosition(position).toString().equals("여성신발")) {
								category2 = "1";
							} else if(parent.getItemAtPosition(position).toString().equals("남성신발")) {
								category2 = "2";
							} else if(parent.getItemAtPosition(position).toString().equals("가방/모자/장갑")) {
								category2 = "3";
							} else if(parent.getItemAtPosition(position).toString().equals("지갑/벨트/시계")) {
								category2 = "4";
							} else if(parent.getItemAtPosition(position).toString().equals("안경/선글라스")) {
								category2 = "5";
							} else if(parent.getItemAtPosition(position).toString().equals("쥬얼리/악세사리")) {
								category2 = "6";
							} else if(parent.getItemAtPosition(position).toString().equals("기타 패션잡화")) {
								category2 = "7";
							}
						}
						@Override
						public void onNothingSelected(AdapterView<?> parent) {
						}					
					});
				} else if(parent.getItemAtPosition(position).toString().equals("미용")) {
					category1 = "4";
					subAdapter = ArrayAdapter.createFromResource(SixActivity.this, R.array.beauty,
							android.R.layout.simple_spinner_item);
					subAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
					spnCategory2.setAdapter(subAdapter);
					
					spnCategory2.setOnItemSelectedListener(new OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							if(parent.getItemAtPosition(position).toString().equals("여성 기초화장품")) {
								category2 = "1";
							} else if(parent.getItemAtPosition(position).toString().equals("여성 색조화장품")) {
								category2 = "2";
							} else if(parent.getItemAtPosition(position).toString().equals("남성 화장품")) {
								category2 = "3";
							} else if(parent.getItemAtPosition(position).toString().equals("향수/아로마")) {
								category2 = "4";
							} else if(parent.getItemAtPosition(position).toString().equals("헤어/바디케어")) {
								category2 = "5";
							} else if(parent.getItemAtPosition(position).toString().equals("요가/운동용품")) {
								category2 = "6";
							} else if(parent.getItemAtPosition(position).toString().equals("기타 미용")) {
								category2 = "7";
							}
						}
						@Override
						public void onNothingSelected(AdapterView<?> parent) {
						}						
					});
				} else if(parent.getItemAtPosition(position).toString().equals("출산/육아")) {
					category1 = "5";
					subAdapter = ArrayAdapter.createFromResource(SixActivity.this, R.array.layette,
							android.R.layout.simple_spinner_item);
					subAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
					spnCategory2.setAdapter(subAdapter);
					
					spnCategory2.setOnItemSelectedListener(new OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							if(parent.getItemAtPosition(position).toString().equals("출산/임부용품")) {
								category2 = "1";
							} else if(parent.getItemAtPosition(position).toString().equals("유아/아동의류")) {
								category2 = "2";
							} else if(parent.getItemAtPosition(position).toString().equals("유아/아동용품")) {
								category2 = "3";
							} else if(parent.getItemAtPosition(position).toString().equals("인형/장난감")) {
								category2 = "4";
							} else if(parent.getItemAtPosition(position).toString().equals("유아책/교육")) {
								category2 = "5";
							}
							
						}
						@Override
						public void onNothingSelected(AdapterView<?> parent) {
						}						
					});
				} else if(parent.getItemAtPosition(position).toString().equals("모바일")) {
					category1 = "6";
					subAdapter = ArrayAdapter.createFromResource(SixActivity.this, R.array.mobile,
							android.R.layout.simple_spinner_item);
					subAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
					spnCategory2.setAdapter(subAdapter);
					
					spnCategory2.setOnItemSelectedListener(new OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							if(parent.getItemAtPosition(position).toString().equals("SKT")) {
								category2 = "1";
							} else if(parent.getItemAtPosition(position).toString().equals("KT")) {
								category2 = "2";
							} else if(parent.getItemAtPosition(position).toString().equals("LGU+")) {
								category2 = "3";
							} else if(parent.getItemAtPosition(position).toString().equals("기타통신사/피처폰")) {
								category2 = "4";
							} else if(parent.getItemAtPosition(position).toString().equals("주변기기/악세사리")) {
								category2 = "5";
							}
						}
						@Override
						public void onNothingSelected(AdapterView<?> parent) {
						}						
					});
				} else if(parent.getItemAtPosition(position).toString().equals("컴퓨터")) {
					category1 = "7";
					subAdapter = ArrayAdapter.createFromResource(SixActivity.this, R.array.computer,
							android.R.layout.simple_spinner_item);
					subAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
					spnCategory2.setAdapter(subAdapter);
					
					spnCategory2.setOnItemSelectedListener(new OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							if(parent.getItemAtPosition(position).toString().equals("노트북/넷북")) {
								category2 = "1";
							} else if(parent.getItemAtPosition(position).toString().equals("데블릿PC")) {
								category2 = "2";
							} else if(parent.getItemAtPosition(position).toString().equals("데스크탑/본체")) {
								category2 = "3";
							} else if(parent.getItemAtPosition(position).toString().equals("모니터")) {
								category2 = "4";
							} else if(parent.getItemAtPosition(position).toString().equals("CPU/메인보드/HDD")) {
								category2 = "5";
							} else if(parent.getItemAtPosition(position).toString().equals("메모리/VGA/ODD")) {
								category2 = "6";
							} else if(parent.getItemAtPosition(position).toString().equals("케이스/키보드마우스")) {
								category2 = "7";
							} else if(parent.getItemAtPosition(position).toString().equals("복합기/프린터")) {
								category2 = "8";
							} else if(parent.getItemAtPosition(position).toString().equals("USB/케이블/스피커")) {
								category2 = "9";
							} else if(parent.getItemAtPosition(position).toString().equals("허브/무선랜/공유기")) {
								category2 = "10";
							} else if(parent.getItemAtPosition(position).toString().equals("잉크/토너/공CD")) {
								category2 = "11";
							} else if(parent.getItemAtPosition(position).toString().equals("PC게임/소프트웨어")) {
								category2 = "12";
							} else if(parent.getItemAtPosition(position).toString().equals("기타 제품/PC파워")) {
								category2 = "13";
							}
						}
						@Override
						public void onNothingSelected(AdapterView<?> parent) {
						}						
					});
				} else if(parent.getItemAtPosition(position).toString().equals("카메라")) {
					category1 = "8";
					subAdapter = ArrayAdapter.createFromResource(SixActivity.this, R.array.camera,
							android.R.layout.simple_spinner_item);
					subAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
					spnCategory2.setAdapter(subAdapter);
					
					spnCategory2.setOnItemSelectedListener(new OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							if(parent.getItemAtPosition(position).toString().equals("DSLR")) {
								category2 = "1";
							} else if(parent.getItemAtPosition(position).toString().equals("미러리스")) {
								category2 = "2";
							} else if(parent.getItemAtPosition(position).toString().equals("일반디카/하이엔드")) {
								category2 = "3";
							} else if(parent.getItemAtPosition(position).toString().equals("필름/중형카메라")) {
								category2 = "4";
							} else if(parent.getItemAtPosition(position).toString().equals("렌즈/필터/컨버터")) {
								category2 = "5";
							} else if(parent.getItemAtPosition(position).toString().equals("삼각대/플래시/조명")) {
								category2 = "6";
							} else if(parent.getItemAtPosition(position).toString().equals("메모리/배터리/가방")) {
								category2 = "7";
							} else if(parent.getItemAtPosition(position).toString().equals("기타 카메라악세사리")) {
								category2 = "8";
							}
						}
						@Override
						public void onNothingSelected(AdapterView<?> parent) {
						}						
					});
				} else if(parent.getItemAtPosition(position).toString().equals("영상기기")) {
					category1 = "9";
					subAdapter = ArrayAdapter.createFromResource(SixActivity.this, R.array.imagingDevice,
							android.R.layout.simple_spinner_item);
					subAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
					spnCategory2.setAdapter(subAdapter);
					
					spnCategory2.setOnItemSelectedListener(new OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							if(parent.getItemAtPosition(position).toString().equals("디지털TV")) {
								category2 = "1";
							} else if(parent.getItemAtPosition(position).toString().equals("DVD/VCR")) {
								category2 = "2";
							} else if(parent.getItemAtPosition(position).toString().equals("PMP/DMB")) {
								category2 = "3";
							} else if(parent.getItemAtPosition(position).toString().equals("셋톱박스/프로젝터")) {
								category2 = "4";
							} else if(parent.getItemAtPosition(position).toString().equals("디지털 캠코더")) {
								category2 = "5";
							} else if(parent.getItemAtPosition(position).toString().equals("편집기기/소프트웨어")) {
								category2 = "6";
							} else if(parent.getItemAtPosition(position).toString().equals("기타 영상관련제품")) {
								category2 = "7";
							}
						}
						@Override
						public void onNothingSelected(AdapterView<?> parent) {	
						}				
					});
				} else if(parent.getItemAtPosition(position).toString().equals("음악/음향/악기")) {
					category1 = "10";
					subAdapter = ArrayAdapter.createFromResource(SixActivity.this, R.array.music,
							android.R.layout.simple_spinner_item);
					subAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
					spnCategory2.setAdapter(subAdapter);
					
					spnCategory2.setOnItemSelectedListener(new OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							if(parent.getItemAtPosition(position).toString().equals("MP3/CDP")) {
								category2 = "1";
							} else if(parent.getItemAtPosition(position).toString().equals("오디오/홈시어터")) {
								category2 = "2";
							} else if(parent.getItemAtPosition(position).toString().equals("헤드폰/스피커/앰프")) {
								category2 = "3";
							} else if(parent.getItemAtPosition(position).toString().equals("기타 음향기기")) {
								category2 = "4";
							} else if(parent.getItemAtPosition(position).toString().equals("LP/CD/DVD 음반")) {
								category2 = "5";
							} else if(parent.getItemAtPosition(position).toString().equals("건반악기")) {
								category2 = "6";
							} else if(parent.getItemAtPosition(position).toString().equals("관악기")) {
								category2 = "7";
							} else if(parent.getItemAtPosition(position).toString().equals("현악기")) {
								category2 = "8";
							} else if(parent.getItemAtPosition(position).toString().equals("타악기")) {
								category2 = "9";
							}
						}
						@Override
						public void onNothingSelected(AdapterView<?> parent) {
						}						
					});
				} else if(parent.getItemAtPosition(position).toString().equals("게임용품")) {
					category1 = "11";
					subAdapter = ArrayAdapter.createFromResource(SixActivity.this, R.array.game,
							android.R.layout.simple_spinner_item);
					subAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
					spnCategory2.setAdapter(subAdapter);
					
					spnCategory2.setOnItemSelectedListener(new OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							if(parent.getItemAtPosition(position).toString().equals("NDSL/WII/닌텐도")) {
								category2 = "1";
							} else if(parent.getItemAtPosition(position).toString().equals("PSP/PS2/PS3/PS4")) {
								category2 = "2";
							} else if(parent.getItemAtPosition(position).toString().equals("XBOX360")) {
								category2 = "3";
							} else if(parent.getItemAtPosition(position).toString().equals("게임소프트웨어")) {
								category2 = "4";
							} else if(parent.getItemAtPosition(position).toString().equals("기타 게임용품")) {
								category2 = "5";
							}
						}
						@Override
						public void onNothingSelected(AdapterView<?> parent) {
						}						
					});
				} else if(parent.getItemAtPosition(position).toString().equals("스포츠/취미")) {
					category1 = "12";
					subAdapter = ArrayAdapter.createFromResource(SixActivity.this, R.array.sports,
							android.R.layout.simple_spinner_item);
					subAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
					spnCategory2.setAdapter(subAdapter);
					
					spnCategory2.setOnItemSelectedListener(new OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							if(parent.getItemAtPosition(position).toString().equals("축구용품/의류")) {
								category2 = "1";
							} else if(parent.getItemAtPosition(position).toString().equals("야구용품/의류")) {
								category2 = "2";
							} else if(parent.getItemAtPosition(position).toString().equals("농구용품/의류")) {
								category2 = "3";
							} else if(parent.getItemAtPosition(position).toString().equals("MTB사이클 용품/의류")) {
								category2 = "4";
							} else if(parent.getItemAtPosition(position).toString().equals("미니벨로/일반자전거")) {
								category2 = "5";
							} else if(parent.getItemAtPosition(position).toString().equals("인라인/스케이트보드")) {
								category2 = "6";
							} else if(parent.getItemAtPosition(position).toString().equals("스키/보드/의류")) {
								category2 = "7";
							} else if(parent.getItemAtPosition(position).toString().equals("골프용품/의류")) {
								category2 = "8";
							} else if(parent.getItemAtPosition(position).toString().equals("헬스/수영/용품/의류")) {
								category2 = "9";
							} else if(parent.getItemAtPosition(position).toString().equals("피규어/프라모델/브릭")) {
								category2 = "10";
							} else if(parent.getItemAtPosition(position).toString().equals("기타스포츠/레저용품")) {
								category2 = "11";
							}
						}
						@Override
						public void onNothingSelected(AdapterView<?> parent) {
						}						
					});
				} else if(parent.getItemAtPosition(position).toString().equals("여행")) {
					category1 = "13";
					subAdapter = ArrayAdapter.createFromResource(SixActivity.this, R.array.travle,
							android.R.layout.simple_spinner_item);
					subAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
					spnCategory2.setAdapter(subAdapter);
					
					spnCategory2.setOnItemSelectedListener(new OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							if(parent.getItemAtPosition(position).toString().equals("등산용품/의류")) {
								category2 = "1";
							} else if(parent.getItemAtPosition(position).toString().equals("캠핑용품/장비")) {
								category2 = "2";
							} else if(parent.getItemAtPosition(position).toString().equals("낚시용품/의류")) {
								category2 = "3";
							} else if(parent.getItemAtPosition(position).toString().equals("숙박권/시설 이용권")) {
								category2 = "4";
							}
						}
						@Override
						public void onNothingSelected(AdapterView<?> parent) {
						}						
					});
				} else if(parent.getItemAtPosition(position).toString().equals("생활용품")) {
					category1 = "14";
					subAdapter = ArrayAdapter.createFromResource(SixActivity.this, R.array.dailySupplies,
							android.R.layout.simple_spinner_item);
					subAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
					spnCategory2.setAdapter(subAdapter);
					
					spnCategory2.setOnItemSelectedListener(new OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							if(parent.getItemAtPosition(position).toString().equals("사무용품/복사기/팩스")) {
								category2 = "1";
							} else if(parent.getItemAtPosition(position).toString().equals("대형가전/TV/냉장고/세탁기/에어컨")) {
								category2 = "2";
							} else if(parent.getItemAtPosition(position).toString().equals("소형가전/밥솥/다리미/가습기/면도기")) {
								category2 = "3";
							} else if(parent.getItemAtPosition(position).toString().equals("침구/커튼/패브릭")) {
								category2 = "4";
							} else if(parent.getItemAtPosition(position).toString().equals("욕실용품/주방용품")) {
								category2 = "5";
							} else if(parent.getItemAtPosition(position).toString().equals("전자사전/MC스퀘어")) {
								category2 = "6";
							} else if(parent.getItemAtPosition(position).toString().equals("상품권/판매가능티켓")) {
								category2 = "7";
							} else if(parent.getItemAtPosition(position).toString().equals("반려동물 용품")) {
								category2 = "8";
							} else if(parent.getItemAtPosition(position).toString().equals("DIY/리폼 용품,공구")) {
								category2 = "9";
							} else if(parent.getItemAtPosition(position).toString().equals("학습도구/문구")) {
								category2 = "10";
							} else if(parent.getItemAtPosition(position).toString().equals("기타 중고")) {
								category2 = "11";
							}
						}
						@Override
						public void onNothingSelected(AdapterView<?> parent) {
						}						
					});
				} else if(parent.getItemAtPosition(position).toString().equals("가구")) {
					category1 = "15";
					subAdapter = ArrayAdapter.createFromResource(SixActivity.this, R.array.furniture,
							android.R.layout.simple_spinner_item);
					subAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
					spnCategory2.setAdapter(subAdapter);
					
					spnCategory2.setOnItemSelectedListener(new OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							if(parent.getItemAtPosition(position).toString().equals("침실/침대/화장대")) {
								category2 = "1";
							} else if(parent.getItemAtPosition(position).toString().equals("거실/쇼파/테이블")) {
								category2 = "2";
							} else if(parent.getItemAtPosition(position).toString().equals("주방/식탁/수납장")) {
								category2 = "3";
							} else if(parent.getItemAtPosition(position).toString().equals("사무용품/책상/서랍")) {
								category2 = "4";
							} else if(parent.getItemAtPosition(position).toString().equals("인테리어소품")) {
								category2 = "5";
							}
						}
						@Override
						public void onNothingSelected(AdapterView<?> parent) {
						}						
					});
				} else if(parent.getItemAtPosition(position).toString().equals("예술/미술")) {
					category1 = "16";
					subAdapter = ArrayAdapter.createFromResource(SixActivity.this, R.array.art,
							android.R.layout.simple_spinner_item);
					subAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
					spnCategory2.setAdapter(subAdapter);
					
					spnCategory2.setOnItemSelectedListener(new OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							if(parent.getItemAtPosition(position).toString().equals("서양/동양/현대작품")) {
								category2 = "1";
							} else if(parent.getItemAtPosition(position).toString().equals("미술재료/물감/마카")) {
								category2 = "2"; 
							} else if(parent.getItemAtPosition(position).toString().equals("미술용품도구")) {
								category2 = "3";
							} else if(parent.getItemAtPosition(position).toString().equals("골동품/기타수집")) {
								category2 = "4";
							} 
						}
						public void onNothingSelected(AdapterView<?> parent) {
						}						
					});
				} else if(parent.getItemAtPosition(position).toString().equals("도서관련")) {
					category1 = "17";
					subAdapter = ArrayAdapter.createFromResource(SixActivity.this, R.array.book,
							android.R.layout.simple_spinner_item);
					subAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
					spnCategory2.setAdapter(subAdapter);
					
					spnCategory2.setOnItemSelectedListener(new OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							if(parent.getItemAtPosition(position).toString().equals("학습/사전/참고서")) {
								category2 = "1";
							} else if(parent.getItemAtPosition(position).toString().equals("문학/과학/경영")) {
								category2 = "2";
							} else if(parent.getItemAtPosition(position).toString().equals("월간/계간/잡지")) {
								category2 = "3";
							} else if(parent.getItemAtPosition(position).toString().equals("여행/취미/레저")) {
								category2 = "4";
							} else if(parent.getItemAtPosition(position).toString().equals("예술/디자인도서")) {
								category2 = "5";
							} else if(parent.getItemAtPosition(position).toString().equals("컴퓨터/인터넷도서")) {
								category2 = "6";
							} else if(parent.getItemAtPosition(position).toString().equals("아동/어린이도서")) {
								category2 = "7";
							} else if(parent.getItemAtPosition(position).toString().equals("소설/만화책")) {
								category2 = "8";
							} else if(parent.getItemAtPosition(position).toString().equals("전집")) {
								category2 = "9";
							}
						}
						@Override
						public void onNothingSelected(AdapterView<?> parent) {							
						}
						
					});
				}
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}			
		}); 
	} 
		
	OnClickListener onClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.btn_next4) {
				System.out.println("체크 : " + category1 + ", " + category2);
				Intent intent = new Intent();
				intent.setClass(SixActivity.this, SevenActivity.class);
				intent.putExtra("category1", category1);
				intent.putExtra("category2", category2);
				startActivity(intent);
			} else if (v.getId() == R.id.btn_previous3) {
				finish();
			}
		}
	};
}
