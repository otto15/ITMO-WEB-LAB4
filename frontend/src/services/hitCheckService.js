import api from "@/services/api";

const PROCESS_HIT_CHECK_POSTFIX = "api/v1/hit-check";
const HIT_CHECKS_POSTFIX = "api/v1/hit-checks";

class HitCheckService {
  getAllHitChecks() {
    return api.get(HIT_CHECKS_POSTFIX).then((response) => {
      return response.data;
    });
  }

  checkHit(dot) {
    return api.post(PROCESS_HIT_CHECK_POSTFIX, {
      x: dot.x,
      y: dot.y,
      r: dot.r,
    });
  }

  deleteAllHitChecks() {
    return api.delete(HIT_CHECKS_POSTFIX);
  }
}

export default new HitCheckService();
