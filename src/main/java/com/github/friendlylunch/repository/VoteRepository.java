package com.github.friendlylunch.repository;

import com.github.friendlylunch.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote v WHERE v.user.id = ?1 ORDER BY v.votingDateTime DESC")
    List<Vote> getAll(int userId);
}
