package ekcsb.transaction_reconciler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {

	public static void main(String[] args) {
		System.out.println("PENDING: --------------------------------------------------");
		Stream<PendingTransaction> pending = createPopulatePendingStream();
		System.out.println("PROCESSED: ------------------------------------------------");
		Stream<Stream<ProcessedTransaction>> processed = createPopulateProcessedStream();

		System.out.println("FILTERED: ------------------------------------------------");
		Stream<PendingTransaction> filtered = Reconciler.reconcile(pending, processed);
		filtered.forEach(e -> System.out.println(e));
	}

	static Stream<PendingTransaction> createPopulatePendingStream() {
		int isNullEmpty = new Random().nextInt(10);

		if (isNullEmpty == 0) {
			return null;
		} else if (isNullEmpty == 1) {
			return Stream.empty();
		} else {
			System.out.println("Pending is 1 to 9 id list");
			return IntStream.range(1, 10).mapToObj(i -> new PendingTransaction(new Long(i)));
		}
	}

	static Stream<Stream<ProcessedTransaction>> createPopulateProcessedStream() {
		int isNullEmpty = new Random().nextInt(10);

		if (isNullEmpty == 0) {
			return null;
		} else if (isNullEmpty == 1) {
			return Stream.empty();
		} else {
			Stream<ProcessedTransaction> innerStream = null;

			List<ProcessedTransaction> processeds = new ArrayList<>();
			IntStream.range(1, 10).forEach(i -> {
				int isNull = new Random().nextInt(6);

				if (isNull == 0) {
					System.out.println("null");
					processeds.add(null);
				} else {
					int isIdNullEmpty = new Random().nextInt(5);
					String id = null;
					if (isIdNullEmpty == 0) {
						id = null;
					} else if (isIdNullEmpty == 1) {
						id = "";
					} else {
						id = String.valueOf(i);
					}

					int isStatusNullLowercase = new Random().nextInt(5);
					Optional<String> status = null;
					if (isStatusNullLowercase == 0) {
						status = null;
					} else if (isStatusNullLowercase == 1) {
						status = Optional.of("DONE");
					} else {
						status = Optional.of("DoNe");
					}

					ProcessedTransaction processed = new ProcessedTransaction(id, status);
					System.out.println(processed);
					processeds.add(processed);
				}
			});
			innerStream = processeds.stream();

			return Stream.of(innerStream);
		}
	}

}

class Reconciler {
	static Stream<PendingTransaction> reconcile(Stream<PendingTransaction> pending,
			Stream<Stream<ProcessedTransaction>> processed) {
		if (pending == null || processed == null) {
			return Stream.empty();
		}

		List<Long> filteredProcessedId = processed.flatMap(p -> p).filter(Objects::nonNull)
				.filter(p -> p.getStatus() != null && p.getStatus().isPresent()
						&& "DONE".equalsIgnoreCase(p.getStatus().orElse(null)))
				.filter(p -> p.getId() != null && p.getId().length() > 0).map(p -> Long.parseLong(p.getId()))
				.collect(Collectors.toList());

		return pending.filter(p -> filteredProcessedId.contains(p.getId()));
	}
}

class PendingTransaction {
	private Long id;

	public PendingTransaction(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PendingTransaction [id=" + id + "]";
	}

}

class ProcessedTransaction {
	private String id;
	private Optional<String> status;

	public ProcessedTransaction(String id, Optional<String> status) {
		this.id = id;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Optional<String> getStatus() {
		return status;
	}

	public void setStatus(Optional<String> status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ProcessedTransaction [id=" + id + ", status=" + status + "]";
	}
}