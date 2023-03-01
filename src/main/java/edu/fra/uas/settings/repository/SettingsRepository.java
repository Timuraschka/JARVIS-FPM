package edu.fra.uas.settings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.fra.uas.settings.model.Settings;

public interface SettingsRepository extends JpaRepository<Settings, Long>{

}
