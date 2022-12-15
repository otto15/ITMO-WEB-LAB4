import api from "@/services/api";

const HIT_CHECKS_POSTFIX = "api/v1/hit-checks";

class HitCheckService {
  getAllHitChecks() {
    return api.get(HIT_CHECKS_POSTFIX).then((response) => {
      console.log(response);
      return response.data.hitChecks;
    });
  }

  checkHit(dot) {
    return api.post(HIT_CHECKS_POSTFIX, {
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
