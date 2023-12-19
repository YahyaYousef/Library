package com.example.demo.repo;

import com.example.demo.domain.entities.UsersImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserImageRepo extends CrudRepository<UsersImage, UUID> {

    Optional<UsersImage> findImageByImageId(UUID imageId);
}
