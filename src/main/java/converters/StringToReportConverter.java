
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ReportRepository;
import domain.Report;

@Component
@Transactional
public class StringToReportConverter extends StringToEntity<Report, ReportRepository> {

}
