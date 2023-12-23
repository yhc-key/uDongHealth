package com.ssafy.udong.model.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.udong.model.dao.PlaceDao;
import com.ssafy.udong.model.dto.Place;
import com.ssafy.udong.model.dto.SearchCondition;

@Service
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	private PlaceDao placeDao;

	@Transactional
	@Override
	public void insertPlace(Place place) {

		// 전체 주소를 구, 동, 상세주소로 나눠서 저장
		// 구
		place.setAddressGu(place.getAddress().split(" ")[1]);
		// 동
		place.setAddressDong(place.getAddress().split(" ")[2]);
		// 상세 주소
		if (place.getAddress().split(" ").length > 3) {
			String str = "";
			int index = 3;
			while (index < place.getAddress().split(" ").length) {
				str += place.getAddress().split(" ")[index] + " ";
				index++;
			}
			place.setAddressDetail(str);
		}

		placeDao.insertPlace(place);
	}

	// 장소 전체 목록 조회
	@Override
	public List<Place> getPlaces() {
		return placeDao.getPlaces();
	}

	// 상세 장소 조회
	@Override
	public Place getPlace(int placeId) {
		return placeDao.getPlaceById(placeId);
	}

	// 검색 기능
	@Override
	public List<Place> search(SearchCondition condition) {
		return placeDao.search(condition);
	}

	// 해시태그로 검색 기능
	@Override
	public List<Place> searchByHashtag(String hashtag) {
		// 장소 전체 불러오기
		List<Place> placeList = placeDao.getPlaces();

		// 필터링된 장소 저장할 리스트
		List<Place> filteredList = new ArrayList<>();

		if (hashtag.equals("온몸")) {
			filteredList = findFitPartPlaces(new String[] { ".*몸.*", ".*바디.*" }, placeList);
		} else if (hashtag.equals("상체")) {
			filteredList = findFitPartPlaces(new String[] { ".*상체.*"}, placeList);
		} else if (hashtag.equals("허리")) {
			filteredList = findFitPartPlaces(new String[] { ".*허리.*", ".*옆.*" }, placeList);
		}else if (hashtag.equals("가슴")) {
			filteredList = findFitPartPlaces(new String[] { ".*가슴.*", ".*바스트.*" }, placeList);
		}else if (hashtag.equals("배")) {
			filteredList = findFitPartPlaces(new String[] { ".*뱃살.*", ".*배.*",".*복근.*" }, placeList);
		}else if (hashtag.equals("어깨")) {
			filteredList = findFitPartPlaces(new String[] { ".*어깨.*", ".*숄더.*" }, placeList);
		}else if (hashtag.equals("등")) {
			filteredList = findFitPartPlaces(new String[] { ".*등.*" }, placeList);
		}else if (hashtag.equals("팔")) {
			filteredList = findFitPartPlaces(new String[] { ".*팔.*", ".*암.*" }, placeList);
		}else if (hashtag.equals("하체")) {
			filteredList = findFitPartPlaces(new String[] { ".*다리.*", ".*레그.*",".*하체.*",".*발.*" }, placeList);
		} else if (hashtag.equals("지구력")) {
			filteredList = findFitPartPlaces(new String[] { ".*봉.*", ".*턱걸이.*", ".*매달리기.*", ".*노젓기.*" }, placeList);
		} else if (hashtag.equals("웨이트")) {
			filteredList = findFitPartPlaces(new String[] { ".*웨이트.*", ".*역기.*",".*당기기.*",".*프레스.*" }, placeList);
		} else if (hashtag.equals("유산소")) {
			filteredList = findFitPartPlaces(new String[] { ".*달리기.*", ".*런닝.*", ".*마라톤.*",".*조깅.*",".*유산소.*",".*걷기.*",".*워킹.*",".*워크.*",".*워커.*",".*스탭.*",".*사이클.*",".*싸이클.*",".*자전거.*" }, placeList);
		} else if (hashtag.equals("스트레칭")) {
			filteredList = findFitPartPlaces(new String[] { ".*지압.*", ".*안마.*",".*마사지.*",".*풀기.*",".*스트레칭.*" }, placeList);
		} else if (hashtag.equals("유연성")) {
			filteredList = findFitPartPlaces(new String[] { ".*유연성.*", ".*돌리기.*",".*트위스트.*",".*스윙.*",".*롤링.*" }, placeList);
		}
		return filteredList;
	}

	// 현재 위치에서 가장 가까운 4곳 조회
	@Override
	public List<Place> findNearPlaces(double latitude, double longitude) {
		return placeDao.findNearPlaces(latitude, longitude);
	}

	
	public List<Place> findFitPartPlaces(String[] patterns, List<Place> placeList) {
		List<Place> filteredList = new ArrayList<>();
		Map<Integer, List<String>> placeMap = new HashMap<>();

		for (Place p : placeList) {
			if (p.getEqKind() == null) {
				continue;
			}
			String[] strList = p.getEqKind().split(",");
			List<String> matchedEqKinds = new ArrayList<>();

			for (String s : strList) {
				for (String pattern : patterns) {
					Pattern regex = Pattern.compile(pattern);
					Matcher matcher = regex.matcher(s);
					if (matcher.find()) {
						matchedEqKinds.add(s);
					}
				}
			}

			// 해당하는 값을 찾았다면
			if (!matchedEqKinds.isEmpty()) {
				int placeId = p.getPlaceId();
				if (placeMap.containsKey(placeId)) {
					placeMap.get(placeId).addAll(matchedEqKinds);
				} else {
					placeMap.put(placeId, new ArrayList<>(matchedEqKinds));
				}
			}
		}
		for (Map.Entry<Integer, List<String>> entry : placeMap.entrySet()) {
			int placeId = entry.getKey();
			List<String> eqKinds = entry.getValue();

			Place tmp = new Place();
			tmp.setPlaceId(placeId);
			tmp.setAddressGu(placeDao.getPlaceById(placeId).getAddressGu());
			tmp.setAddressDong(placeDao.getPlaceById(placeId).getAddressDong());
			tmp.setPlaceName(placeDao.getPlaceById(placeId).getPlaceName());
			tmp.setCnt(eqKinds.size());

			// EqKinds를 하나의 문자열로 만들어 Place에 설정
			String eqKindsCombined = String.join(",", eqKinds);
			tmp.setEqKind(eqKindsCombined);

			filteredList.add(tmp);
		}
		
		// filteredList를 cnt가 큰 순서대로 정렬
		Collections.sort(filteredList, new Comparator<Place>() {
		    @Override
		    public int compare(Place place1, Place place2) {
		        return Integer.compare(place2.getCnt(), place1.getCnt());
		    }
		});

		// 정렬된 filteredList 반환		
		return filteredList;

	} // findFitPartList

}
