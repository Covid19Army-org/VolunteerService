package com.covid19army.VolunteerService.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.covid19army.VolunteerService.models.Volunteer;

public interface VolunteerRepository extends CrudRepository<Volunteer, Long> {

	@Query("Select v from Volunteer v Join v.volunteerareas va "
			+ "where ( va.state = :state and va.district = :district and va.availabilityPincodes LIKE %:pincode%)"
			+ "or ( va.state = :state and va.district = :district and (va.availabilityPincodes is null or va.availabilityPincodes = ''))"
			+ " or ( va.state = :state and (va.district is null or va.district = '') and (va.availabilityPincodes is null or va.availabilityPincodes = ''))")
	Page<Volunteer> findByVolunteerArea(@Param("state") String state,
			@Param("district") String district, @Param("pincode") String pincode, Pageable pageable);
	
	@Query("Select v.volunteerid from Volunteer v Join v.volunteerareas va "
			+ "where ( va.state = :state and va.district = :district and va.availabilityPincodes LIKE %:pincode%)"
			+ "or ( va.state = :state and va.district = :district and (va.availabilityPincodes is null or va.availabilityPincodes = ''))"
			+ " or ( va.state = :state and (va.district is null or va.district = '') and (va.availabilityPincodes is null or va.availabilityPincodes = ''))")
	Page<Long> findVolunteerIdByVolunteerArea(@Param("state") String state,
			@Param("district") String district, @Param("pincode") String pincode, Pageable pageable);
}
