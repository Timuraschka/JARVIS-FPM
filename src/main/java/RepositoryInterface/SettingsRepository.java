package RepositoryInterface;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.fra.uas.model.Project;
import edu.fra.uas.model.Settings;

public interface SettingsRepository extends JpaRepository<Settings, Project>{

}
