package com.covid19army.VolunteerService.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.covid19army.VolunteerService.models.Volunteer;
import com.covid19army.core.enums.NeedsEnum;

public interface VolunteerRepository extends CrudRepository<Volunteer, Long> {

	@Query("Select distinct v from Volunteer v Join v.volunteerareas va "
			+ "where ( va.state = :state and va.district = :district and va.availabilityPincodes LIKE %:pincode%)"
			+ "or ( va.state = :state and va.district = :district and (va.availabilityPincodes is null or va.availabilityPincodes = ''))"
			+ " or ( va.state = :state and (va.district is null or va.district = '') and (va.availabilityPincodes is null or va.availabilityPincodes = ''))")
	Page<Volunteer> findByVolunteerArea(@Param("state") String state,
			@Param("district") String district, @Param("pincode") String pincode, Pageable pageable);
	
	@Query("Select distinct v.volunteerid from Volunteer v Join v.volunteerareas va "
			+ "where ( va.state = :state and va.district = :district and va.availabilityPincodes LIKE %:pincode%)"
			+ "or ( va.state = :state and va.district = :district and (va.availabilityPincodes is null or va.availabilityPincodes = ''))"
			+ " or ( va.state = :state and (va.district is null or va.district = '') and (va.availabilityPincodes is null or va.availabilityPincodes = ''))")
	Page<Long> findVolunteerIdByVolunteerArea(@Param("state") String state,
			@Param("district") String district, @Param("pincode") String pincode, Pageable pageable);
	
	
	@Query("Select distinct v.volunteerid from Volunteer v Join v.volunteerareas va Join v.volunteerprovidedneeds n "
			+ "where (( va.state = :state and va.district = :district and va.availabilityPincodes LIKE %:pincode%) "
			+ "or ( va.state = :state and va.district = :district and (va.availabilityPincodes is null or va.availabilityPincodes = '')) "
			+ " or ( va.state = :state and (va.district is null or va.district = '') and (va.availabilityPincodes is null or va.availabilityPincodes = '')))"
			+ " and n.needid in(:needs)")
	Page<Long> findVolunteerIdByVolunteerAreaAndNeeds(@Param("state") String state,
			@Param("district") String district, @Param("pincode") String pincode, List<NeedsEnum> needs, Pageable pageable);
}
