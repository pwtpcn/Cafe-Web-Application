package ku.cs.cafe.repository;

import ku.cs.cafe.common.Status;
import ku.cs.cafe.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, UUID> {
    PurchaseOrder findByStatus(Status status);
}

