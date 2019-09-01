
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Report;

@Component
@Transactional
public class ReportToStringConverter extends EntityToString<Report> {
}
