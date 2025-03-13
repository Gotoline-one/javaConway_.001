
# Next Steps for Class Deliverable

## ✅ Goal:
Prepare a **working prototype** and **formal proposal** suitable for submission as an MS CS capstone, focusing on **graphical and real-time performance benchmarking** between Java 8 and Java 22 using Game of Life as a demonstrator.

---

## 🔹 1. Benchmarking Focus (Graphics + FPS)

- [ ] **Add FPS measurement** to Game of Life in both Java 8 and Java 22 versions.
- [ ] Add simple output mechanism (e.g., CSV or JSON) to capture:
  - Frame counts
  - System.nanoTime() or System.currentTimeMillis() for each frame
  - Basic hardware info (Java version, OS, CPU)
- [ ] Ensure Game of Life runs for a **fixed period** and writes results on exit.
- [ ] Optionally use terminal timing (`/usr/bin/time`) for system-level benchmarks.

---

## 🔹 2. Repository Cleanup and Linking

- [ ] Ensure **rt-container-system repo** is fully pushed and public.
- [ ] Ensure **javaConway_.001** is correctly linked as a submodule (this is done but double-check).
- [ ] Add a note in the **README** about what specifically is being used from Game of Life repo for benchmarking.

---

## 🔹 3. Write-up and Formal Capstone Proposal

- [ ] Extract and polish **design and timeline** from `docs/architecture.md` for use in formal capstone proposal.
- [ ] Prepare **brief summary of benchmarking plan** as a deliverable (focus on graphics performance between Java 8 and Java 22).
- [ ] Outline expected **final capstone submission**: working prototype, analysis of results, and system design document.

---

## 🔹 4. Optional Stretch Goals (if time allows)

- [ ] Add **basic runtime stats tracking** in Game of Life (optional for now).
- [ ] Prepare initial **CLI runner script** for JavaFX versions to allow easy testing and comparison.

---

## 📅 Suggested Order of Work

1. [ ] **Finalize and commit** FPS output + run duration limit for Game of Life.
2. [ ] Test Java 8 and Java 22 versions for comparison runs.
3. [ ] Review and polish repo README and architecture docs.
4. [ ] Draft and submit formal capstone proposal using existing materials.
