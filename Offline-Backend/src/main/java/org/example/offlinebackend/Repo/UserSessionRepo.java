package org.example.offlinebackend.Repo;

import org.example.offlinebackend.Model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionRepo extends JpaRepository<UserSession,String> {

}
